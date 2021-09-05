package com.example.birdwatcher.service.impl;

import com.example.birdwatcher.general.Bird;
import com.example.birdwatcher.general.Nest;
import com.example.birdwatcher.repository.BirdRepo;
import com.example.birdwatcher.service.BirdService;
import com.example.birdwatcher.service.NestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean save(Bird bird, long idNest) {
        Nest nest = nestService.getById(idNest);
        if (nest.isExistBird(bird)) {
            return false;
        }
        List<Bird> currentListOfBirds = nest.getBirds();
        currentListOfBirds.add(bird);
        nest.setBirds(currentListOfBirds);
        nestService.update(nest);
        return true;
    }

    @Override
    public void update(Bird bird) {
        Bird birdToUpdate = birdRepo.getById(bird.getId());
        birdToUpdate.setName(bird.getName());
        birdToUpdate.setFlyingBird(bird.isFlyingBird());
        birdToUpdate.setColor(bird.getColor());
        birdRepo.save(birdToUpdate);
    }

    @Override
    public void delete(Bird bird) {
        birdRepo.delete(bird);
    }

}
