package com.example.birdwatcher.service;


import com.example.birdwatcher.model.Bird;

import java.util.List;

public interface BirdService {
    List<Bird> getAllBird();

    Bird getById(long id);

    boolean save(Bird bird, long idNest);

    void update(Bird bird, long id);

    void delete(Bird bird);

}
