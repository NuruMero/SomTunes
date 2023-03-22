package com.example.song.service;

import com.example.song.controller.dto.SongDto;
import org.springframework.http.ResponseEntity;

public interface SongService {

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> getOneById(Integer ID);

    public ResponseEntity<?> getBandSongs(Integer ID);

    public ResponseEntity<?> createOne(SongDto dto);

    public ResponseEntity<?> editOne(SongDto dto, Integer ID);

    public ResponseEntity<?> deleteOne(Integer ID);
}
