package com.example.song.service;

import com.example.song.controller.dto.BandFillableDTO;
import org.springframework.http.ResponseEntity;

public interface BandService {
    public ResponseEntity<?> findAll();

    public ResponseEntity<?> getOneById(Integer ID);

    public ResponseEntity<?> newOne(BandFillableDTO dto);

    public ResponseEntity<?> editOne(BandFillableDTO dto, Integer ID);

    public ResponseEntity<?> deleteOne(Integer ID);
}
