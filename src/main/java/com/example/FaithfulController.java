package com.example;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class FaithfulController {
    //Eat food alone
    @Timed
    @RequestMapping(value = "/eat/{food}", method = RequestMethod.GET)
    public void eat(@PathVariable("food") String food) throws IOException {
        if(food == null)
            System.out.println("Sometimes I pray before eating some food!");
        else
            System.out.println("Sometimes I pray before eating some " + food + "!");
    }

    //Eat with friends
    @Timed
    @RequestMapping(value = "/eatWithFriends/{food}", method = RequestMethod.GET)
    public void eatWithFriends(@PathVariable("food") String food) throws IOException {
        if(food == null)
            System.out.println("I usually don't pray when eating food with friends!");
        else
            System.out.println("I usually don't pray when eating " + food + " with friends!");
    }

    //Study
    @Timed
    @RequestMapping(value = "/study/{subject}", method = RequestMethod.GET)
    public void study(@PathVariable("subject") String subject) throws IOException {
        if(subject == null)
            System.out.println("I don't usually pray when I am studying for class!");
        else
            System.out.println("I don't usually pray when I am studying for " + subject + "!");
    }

    //Meet with friends
    @Timed
    @RequestMapping(value = "/meetWithFriends/{place}", method = RequestMethod.GET)
    public void meetWithFriends(@PathVariable("place") String place) throws IOException {
        if(place == null)
            System.out.println("Sometimes I pray before meeting with friends!");
        else
            System.out.println("Sometimes I pray before meeting with friends at " + place + "!");
    }

    //Play
    @Timed
    @RequestMapping(value = "/play/{place}", method = RequestMethod.GET)
    public void play(@PathVariable("place") String place) throws IOException {
        if(place == null)
            System.out.println("Sometimes I pray before I play!");
        else
            System.out.println("Sometimes I pray before I play at " + place + "!");
    }
}