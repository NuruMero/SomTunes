package com.example.song.controller.impl;

import com.example.song.controller.BandAPI;
import com.example.song.controller.dto.BandDto;
import com.example.song.exception.DuplicatedUniqueObjectException;
import com.example.song.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BandController implements BandAPI {
    private final BandService bandService;

    @Override
    public ResponseEntity<?> findAll() {
        List<BandDto> bandList = bandService.findAll();
        if (bandList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bandList);
        }
    }

    @Override
    public ResponseEntity<?> getOneById(Integer id) {
        BandDto dto = bandService.getOneById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @Override
    public ResponseEntity<?> createOne(BandDto dto) throws DuplicatedUniqueObjectException {
        BandDto result = bandService.createOne(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    public ResponseEntity<?> editOne(BandDto dto, Integer id) throws DuplicatedUniqueObjectException {
        BandDto result = bandService.editOne(dto, id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer id) {
        boolean result = bandService.deleteOne(id);
        if (!result) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
