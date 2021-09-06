package com.example.birdwatcher.service.impl;

import com.example.birdwatcher.model.Bird;
import com.example.birdwatcher.model.Nest;
import com.example.birdwatcher.model.exception.BirdIsAlreadyAssignedException;
import com.example.birdwatcher.repository.BirdRepo;
import com.example.birdwatcher.service.BirdService;
import com.example.birdwatcher.service.NestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BirdServiceImpl implements BirdService {

    @Autowired
    BirdRepo birdRepo;

    @Autowired
    private NestService nestService;


    @Override
    public List<Bird> getAllBird() {
        return birdRepo.findAll();
    }

    @Override
    public Bird getById(long id) {
        return birdRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public boolean save(Bird bird, long idNest) {
        Nest nest = nestService.getById(idNest);
        if (nest.isExistBird(bird)) {
            throw new BirdIsAlreadyAssignedException(bird, idNest);
        }
        List<Bird> currentListOfBirds = nest.getBirds();
        currentListOfBirds.add(bird);
        nest.setBirds(currentListOfBirds);
        nestService.update(nest);
        return true;
    }

    @Override
    @Transactional
    public void update(Bird bird, long id) {
        Bird birdToUpdate = birdRepo.getById(id);
        birdToUpdate.setName(bird.getName());
        birdToUpdate.setIsFlyingBird(bird.getIsFlyingBird());
        birdToUpdate.setColor(bird.getColor());
        birdRepo.save(birdToUpdate);
    }

    @Override
    @Transactional
    public void delete(Bird bird) {
        birdRepo.delete(bird);
    }

}
