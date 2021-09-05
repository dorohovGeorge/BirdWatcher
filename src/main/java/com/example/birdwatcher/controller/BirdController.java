package com.example.birdwatcher.controller;

import com.example.birdwatcher.general.Bird;
import com.example.birdwatcher.service.BirdService;
import com.example.birdwatcher.service.NestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/birds")
public class BirdController {

    private final Logger LOG = LoggerFactory.getLogger(BirdController.class);

    @Autowired
    private BirdService birdService;


    @GetMapping
    public ResponseEntity<List<Bird>> getAll() {
        LOG.info("Getting all birds");
        List<Bird> birds = birdService.getAllBird();
        if (birds == null || birds.isEmpty()) {
            return new ResponseEntity<List<Bird>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Bird>>(birds, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Bird> get(@PathVariable("id") long id) {
        LOG.info("Getting bird with id: {}", id);
        Bird bird = birdService.getById(id);
        if (bird == null) {
            return new ResponseEntity<Bird>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Bird>(bird, HttpStatus.OK);
    }

    @PostMapping(path = "/{id_nest}")
    public ResponseEntity<Void> create(@RequestBody Bird bird, @PathVariable("id_nest") long id_nest,
                                       UriComponentsBuilder ucBuilder) {
        LOG.info("Creating bird: {}", bird);
        if (bird == null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        boolean status = birdService.save(bird, id_nest);
        if (!status) {
            LOG.info("This bird already exist in nest with id: {}", id_nest);
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/bird/{id}").buildAndExpand(bird.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Bird> update(@RequestBody Bird bird) {
        LOG.info("Updating bird with id: {}", bird.getId());
        if (birdService.getById(bird.getId()) == null) {
            return new ResponseEntity<Bird>(HttpStatus.NOT_FOUND);
        }
        birdService.update(bird);
        return new ResponseEntity<Bird>(bird, HttpStatus.OK);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id) {
        LOG.info("Delete bird with id: {}", id);
        Bird bird = birdService.getById(id);
        if (bird == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        birdService.delete(bird);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
