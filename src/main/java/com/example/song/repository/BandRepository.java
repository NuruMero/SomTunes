package com.example.song.repository;

import com.example.song.entity.BandEntity;
import com.example.song.repository.custom.BandRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends JpaRepository<BandEntity, Integer>, BandRepositoryCustom {

    BandEntity findByName(String name);

}