package com.example.birdwatcher.service;

import com.example.birdwatcher.model.Bird;
import com.example.birdwatcher.model.Nest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NestServiceTest {

    @Autowired
    NestService nestService;

    @Test
    public void getAll() {
        List<Nest> nest = nestService.getAllNest();
        assertThat(nest).hasSize(1);
    }

    @Test
    public void get() {
        Nest nest = nestService.getById(1);
        assertThat(nest.getName()).isEqualTo("Obryv");
    }

    @Test
    public void save() {
        List<Nest> nests = nestService.getAllNest();
        Nest nest = new Nest("Mount", "Erebor");
        nestService.save(nest);
        assertThat(nestService.getAllNest().size()).isEqualTo(nests.size() + 1);
    }

    @Test
    public void update() {
        Nest nest = new Nest("Mori", " Middle-earth");
        nestService.save(nest);
        nest.setName("Moria");
        nestService.update(nest);
        assertThat(nestService.getById(3).getName()).isEqualTo("Moria");
    }

    @Test
    public void delete() {
        Nest nest = new Nest("Isengard", " Middle-earth");
        nestService.save(nest);
        Nest finalNest = nest;
        nest = nestService.getAllNest().stream().filter(nest1 -> nest1.equals(finalNest)).findFirst().get();
        assertThat(nestService.getAllNest()).hasSize(2);
        nestService.delete(nest);
        assertThat(nestService.getAllNest()).hasSize(1);
    }
}
