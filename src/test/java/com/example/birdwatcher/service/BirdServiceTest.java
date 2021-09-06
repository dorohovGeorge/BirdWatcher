package com.example.birdwatcher.service;

import com.example.birdwatcher.model.Bird;
import com.example.birdwatcher.model.Nest;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BirdServiceTest {
    @Autowired
    BirdService birdService;

    @Test
    public void getAll() throws Exception {
        List<Bird> birds = birdService.getAllBird();
        assertThat(birds).hasSize(2);
    }

    @Test
    public void get() throws Exception {
        List<Bird> birds = birdService.getAllBird();
        assertThat(birds.get(0).getName()).isEqualTo("Parrot");
    }

    @Test
    public void save() {
        List<Bird> birds = birdService.getAllBird();
        Bird testBird = new Bird("Crow", "Black", true);

        birdService.save(testBird, 1);

        assertThat(birdService.getAllBird()).hasSize(birds.size() + 1);
    }

    @Test
    public void update() {
        Bird bird = new Bird("Pigeon", "Grey", true);
        birdService.save(bird, 1);
        Bird finalBird = bird;
        bird = birdService.getAllBird().stream().filter(bird1 -> bird1.equals(finalBird)).findFirst().get();
        bird.setName("Popka");

        birdService.update(bird);

        assertThat(birdService.getById(bird.getId()).getName()).isEqualTo("Popka");
    }
    @Test
    public void delete() {
        Bird bird = new Bird("Hawk", "Brown", true);
        birdService.save(bird, 1);
        Bird finalBird = bird;
        bird = birdService.getAllBird().stream().filter(bird1 -> bird1.equals(finalBird)).findFirst().get();
        assertThat(birdService.getAllBird()).hasSize(3);
        birdService.delete(bird);
        assertThat(birdService.getAllBird()).hasSize(2);
    }

}
