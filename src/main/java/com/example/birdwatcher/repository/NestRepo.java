package com.example.birdwatcher.repository;

import com.example.birdwatcher.general.Nest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NestRepo extends JpaRepository<Nest, Long> {
}
