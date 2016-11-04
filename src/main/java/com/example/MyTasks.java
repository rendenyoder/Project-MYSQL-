package com.example;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
@EnableScheduling
public class MyTasks {
    //Create rest template object
    RestTemplate restTemplate = new RestTemplate();

    //Add vehicle at given interval
    @Scheduled(cron="*/10 * * * * *")
    public void addVehicle(){
        //Create random object
        Random random = new Random();
        //Generate Random string for make and model
        String makeModel = "";
        for(int i = 0; i < 20; i++)
            makeModel += (char)((char)random.nextInt(122 - 97) + 97);
        //Create new vehicle from random variables
        Vehicle vehicle = new Vehicle(null, makeModel, random.nextInt(2016 - 1986) + 1986, random.nextInt(45000 - 15000) + 15000);
        //Send post request
        restTemplate.postForObject("http://localhost:8080/addVehicle", vehicle, Vehicle.class);
        //Print action details
        System.out.println("VEHICLE ADDED: " + vehicle.toJSON());
    }

    //Add update vehicles at interval
    @Scheduled(cron="25 * * * * *")
    public void updateVehicle(){
        //Create random object
        Random random = new Random();
        int id = random.nextInt(100);
        try {
            //Get the planned updated vehicle's details and print action
            Vehicle vehicleToUpdate = restTemplate.getForObject("http://localhost:8080/getVehicle/" + id, Vehicle.class);
            System.out.println("UPDATING VEHICLE: " + vehicleToUpdate.toJSON());
            Vehicle updateVehicle = new Vehicle(vehicleToUpdate.getId(), "MODIFIED MAKEMODEL", 9999, 0.0);
            //Delete car with given id
            restTemplate.put("http://localhost:8080/updateVehicle/", updateVehicle, Vehicle.class);
        }catch (Exception e){
            System.out.println("VEHICLE WITH ID " + id + " NOT FOUND. NO VEHICLES WERE UPDATED");
        }
    }

    //Delete vehicle at given interval
    @Scheduled(cron="45 * * * * *")
    public void deleteVehicle(){
        //Create random object
        Random random = new Random();
        int id = random.nextInt(100);
        try {
            //Get the planned deleted vehicle's details and print action
            Vehicle vehicleToDelete = restTemplate.getForObject("http://localhost:8080/getVehicle/" + id, Vehicle.class);
            System.out.println("DELETING VEHICLE: " + vehicleToDelete.toJSON());
            //Delete vehicle with given id
            restTemplate.delete("http://localhost:8080/deleteVehicle/" + id);
        }catch (Exception e){
            System.out.println("VEHICLE WITH ID " + id + " NOT FOUND. NO VEHICLES WERE DELETED");
        }
    }
}