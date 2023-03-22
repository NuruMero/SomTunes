package com.example.song.repository;

import com.example.song.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer> {

    @Query(value = "SELECT * FROM song s WHERE s.band_id = :id", nativeQuery = true)
    List<SongEntity> findByBand(@Param("id") Integer ID);
}
