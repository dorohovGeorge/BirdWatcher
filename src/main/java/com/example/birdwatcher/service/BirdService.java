package com.example.birdwatcher.service;


import com.example.birdwatcher.general.Bird;

import java.util.List;

public interface BirdService {
    List<Bird> getAllBird();

    Bird getById(long id);

    boolean save(Bird bird, long idNest);

    void update(Bird bird);

    void delete(Bird bird);

}
