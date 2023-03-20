package com.example.song.repository;

import com.example.song.entity.BandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<BandEntity, Integer> {
    Optional<BandEntity> findByName(String name);
}