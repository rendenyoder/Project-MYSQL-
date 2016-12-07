package com.example;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Component
@EnableScheduling
public class MyFaithfulTasks {
    //Create rest template object
    RestTemplate restTemplate = new RestTemplate();
    Random r = new Random();

    //Lists to hold foods, places, subjects
    ArrayList<String> foods = new ArrayList<>(Arrays.asList("cake", "soup", "cereal", "fruit", "granola"));
    ArrayList<String> places = new ArrayList<>(Arrays.asList("the mall", "the store", "my home", "the ocean", "the coffee shop"));
    ArrayList<String> subjects = new ArrayList<>(Arrays.asList("Statistics", "English", "Art", "Astronomy", "Science"));

    //Call eat at interval
    @Scheduled(cron="55 * * * * *")
    public void eating(){
        restTemplate.getForObject("http://localhost:8080/eat/" + selectRandomObject(foods), String.class);
    }

    //Call eatWithFriends at interval
    @Scheduled(cron="55 * * * * *")
    public void eatingWithFriends(){
        restTemplate.getForObject("http://localhost:8080/eatWithFriends/" + selectRandomObject(foods), String.class);
    }

    //Call studying at interval
    @Scheduled(cron="55 * * * * *")
    public void studying(){
        restTemplate.getForObject("http://localhost:8080/study/" + selectRandomObject(subjects), String.class);
    }

    //Call meetWithFriends at interval
    @Scheduled(cron="55 * * * * *")
    public void meetingWithFriends(){
        restTemplate.getForObject("http://localhost:8080/meetWithFriends/" + selectRandomObject(places), String.class);
    }

    //Call playing at interval
    @Scheduled(cron="55 * * * * *")
    public void playing(){
        restTemplate.getForObject("http://localhost:8080/play/" + selectRandomObject(places), String.class);
    }

    //Get random object from one of the lists
    private String selectRandomObject(ArrayList<String> x){
        return x.get(r.nextInt(x.size()));
    }
}