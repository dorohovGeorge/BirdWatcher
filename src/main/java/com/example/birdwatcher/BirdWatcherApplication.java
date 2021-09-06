package com.example.birdwatcher;

import com.example.birdwatcher.model.Bird;
import com.example.birdwatcher.model.Nest;
import com.example.birdwatcher.service.NestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BirdWatcherApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BirdWatcherApplication.class, args);
    }

    @Autowired
    NestService nestService;

    @Override
    public void run(String... args) {
        Bird popug = new Bird("Parrot", "Green", true);
        Bird lebed = new Bird("Sven", "White", true);
        Nest nest = new Nest("Obryv", "Saint-Petersburg");
        nest.setBirds(Arrays.asList(popug, lebed));
        nestService.save(nest);
    }
}
