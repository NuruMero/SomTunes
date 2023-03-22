package com.example.song.service.impl;

import com.example.song.controller.dto.BandDto;
import com.example.song.controller.dto.mapper.BandMapper;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepo;
    private final SongRepository songRepo;
    private final BandMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        List<BandEntity> bandlist = bandRepo.findAll();
        if (bandlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<BandDto> dtoList = new ArrayList<>();
            bandlist.forEach(band -> dtoList.add(mapper.toDto(band)));
            return ResponseEntity.ok(dtoList);
        }
    }

    @Override
    public ResponseEntity<?> getOneById(Integer ID) {
        BandEntity band = bandRepo.findById(ID).orElse(null);
        if (band == null) {
            return ResponseEntity.notFound().build();
        } else {
            BandDto dto = mapper.toDto(band);
            return ResponseEntity.ok(dto);
        }
    }

    @Override
    public ResponseEntity<?> createOne(BandDto dto) {
        BandEntity newBand = mapper.toEntity(dto);
        try {
            bandRepo.save(newBand);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR: Duplicate name found. The entity already exists.");
        }
    }

    @Override
    public ResponseEntity<?> editOne(BandDto dto, Integer ID) {
        return bandRepo.findById(ID).map(band -> {
            band = mapper.toEntity(dto);
            band.setId(ID);
            bandRepo.save(band);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer ID) {
        if (bandRepo.findById(ID).orElse(null) != null) {
            List<SongEntity> songlist = songRepo.findByBand(ID);
            if (!songlist.isEmpty()) {
                songlist.forEach(songRepo::delete);
            }
            bandRepo.deleteById(ID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
