package com.example.song.service;

import com.example.song.controller.dto.BandDto;
import org.springframework.http.ResponseEntity;

public interface BandService {
    public ResponseEntity<?> findAll();

    public ResponseEntity<?> getOneById(Integer ID);

    public ResponseEntity<?> createOne(BandDto dto);

    public ResponseEntity<?> editOne(BandDto dto, Integer ID);

    public ResponseEntity<?> deleteOne(Integer ID);
}
