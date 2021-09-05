package com.example.birdwatcher.service;

import com.example.birdwatcher.general.Bird;
import com.example.birdwatcher.repository.BirdRepo;
import com.example.birdwatcher.repository.NestRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BirdServiceTest {
    @Autowired
    BirdService birdService;

    @Test
    public void getAll() throws Exception {
        /*List<Bird> birds = birdService.getAllBird();
        assertThat(birds).hasSize(2);
        assertThat(birds.get(0).getName().equals("Popka"));
        assertThat(birds.get(0).getName().equals("Lebed"));*/
    }

    @Test
    public void get(long id) throws Exception {
    }
}
