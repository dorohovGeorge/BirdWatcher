package com.example.birdwatcher.service.impl;

import com.example.birdwatcher.model.Bird;
import com.example.birdwatcher.model.Nest;
import com.example.birdwatcher.repository.NestRepo;
import com.example.birdwatcher.service.BirdService;
import com.example.birdwatcher.service.NestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NestServiceImpl implements NestService {
    @Autowired
    BirdService birdService;

    @Autowired
    NestRepo nestRepo;

    @Override
    @Transactional
    public List<Nest> getAllNest() {
        return nestRepo.findAll();
    }

    @Override
    @Transactional
    public Nest getById(long id) {
        return nestRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void save(Nest nest) {
        nestRepo.save(nest);
    }

    @Override
    @Transactional
    public void update(Nest nest) {
        Nest nestToUpdate = nestRepo.getById(nest.getId());
        nestToUpdate.setName(nest.getName());
        nestToUpdate.setAddress(nest.getAddress());
        nestToUpdate.setBirds(nest.getBirds());
        nestRepo.save(nestToUpdate);
    }

    @Override
    @Transactional
    public void delete(Nest nest) {
        nestRepo.delete(nest);
    }

    @Transactional
    public Nest addBirdToNest(Long nestId, Long birdId) {
        Nest nest = getById(nestId);
        Bird bird = birdService.getById(birdId);
        nest.addBird(bird);
        return nest;
    }

    @Transactional
    public Nest removeBirdFromNest(Long nestId, Long birdId) {
        Nest nest = getById(nestId);
        Bird bird = birdService.getById(birdId);
        nest.removeBird(bird);
        return nest;
    }

}
