package com.example.song.service;

import com.example.song.controller.dto.SongFillableDTO;
import org.springframework.http.ResponseEntity;

public interface SongService {

    public ResponseEntity<?> findAll();

    public ResponseEntity<?> getOneById(Integer ID);

    public ResponseEntity<?> newOne(SongFillableDTO dto);

    public ResponseEntity<?> editOne(SongFillableDTO dto, Integer ID);

    public ResponseEntity<?> deleteOne(Integer ID);
}
