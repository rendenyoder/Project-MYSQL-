package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@RestController
public class Controller {
    @Autowired
    private VehicleDao vehicleDao;

    //Get Vehicle with given id from database
    @Timed
    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) throws IOException {
        //Force id to null
        vehicle.setId(null);
        vehicleDao.add(vehicle);
        return vehicle;
    }

    //Get Vehicle with given id from database
    @Timed
    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle) throws IOException {
        return vehicleDao.updateVehicle(vehicle);
    }

    //Get vehicle with given id
    @Timed
    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        return vehicleDao.getById(id);
    }

    //Delete vehicle with given id from the database
    @Timed
    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        return vehicleDao.deleteById(id);
    }
}