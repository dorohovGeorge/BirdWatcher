package com.example.birdwatcher.controller;

import com.example.birdwatcher.general.Nest;
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
@RequestMapping("/nests")
public class NestController {
    private final Logger LOG = LoggerFactory.getLogger(NestController.class);

    @Autowired
    private NestService nestService;

    @GetMapping
    public ResponseEntity<List<Nest>> getAll() {
        LOG.info("Getting all nests");
        List<Nest> nests = nestService.getAllNest();
        if (nests != null || nests.isEmpty()) {
            return new ResponseEntity<List<Nest>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Nest>>(nests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nest> get(@PathVariable("id") long id) {
        LOG.info("Getting nest with id: {}", id);
        Nest nest = nestService.getById(id);
        if (nest == null) {
            return new ResponseEntity<Nest>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Nest>(nest, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Nest> update(@PathVariable("id") long id, @RequestBody Nest nest) {
        LOG.info("Updating nest with id: {}", id);
        if (nestService.getById(id) == null) {
            return new ResponseEntity<Nest>(HttpStatus.NOT_FOUND);
        }
        nestService.update(nest);
        return new ResponseEntity<Nest>(nest, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Nest nest, UriComponentsBuilder ucBuilder) {
        LOG.info("Creating nest: {}", nest);
        if (nest == null) {
            LOG.info("This nest is empty");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        nestService.save(nest);
        LOG.info("Save nest: {}", nest);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/nest/{id}").buildAndExpand(nest.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id) {
        LOG.info("Delete nest with id: {}", id);
        Nest nest = nestService.getById(id);
        if (nest == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        nestService.delete(nest);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
