package com.example.song.controller.impl;

import com.example.song.controller.BandAPI;
import com.example.song.controller.dto.BandDto;
import com.example.song.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BandController implements BandAPI {
    private final BandService bandService;

    @Override
    public ResponseEntity<?> findAll() {
        return bandService.findAll();
    }

    @Override
    public ResponseEntity<?> getOneById(Integer id) {
        return bandService.getOneById(id);
    }

    @Override
    public ResponseEntity<?> createOne(BandDto dto) {
        return bandService.createOne(dto);
    }

    @Override
    public ResponseEntity<?> editOne(BandDto dto, Integer id) {
        return bandService.editOne(dto, id);
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer id) {
        return bandService.deleteOne(id);
    }
}
