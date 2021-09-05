package com.example.birdwatcher.repository;

import com.example.birdwatcher.general.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepo extends JpaRepository<Bird, Long> {
}
