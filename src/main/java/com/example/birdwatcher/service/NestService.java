package com.example.birdwatcher.service;

import com.example.birdwatcher.model.Nest;

import java.util.List;

public interface NestService {
    List<Nest> getAllNest();

    Nest getById(long id);

    void save(Nest nest);

    void update(Nest nest);

    void delete(Nest nest);
}
