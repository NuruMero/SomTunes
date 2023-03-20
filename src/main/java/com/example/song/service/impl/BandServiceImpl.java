package com.example.song.service.impl;

import com.example.song.controller.dto.BandFillableDTO;
import com.example.song.controller.dto.converter.BandConverter;
import com.example.song.entity.BandEntity;
import com.example.song.repository.BandRepository;
import com.example.song.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepo;
    private final BandConverter bandConverter;

    @Override
    public ResponseEntity<?> findAll() {
        List<BandEntity> bandlist = bandRepo.findAll();
        if (bandlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bandlist);
        }
    }

    @Override
    public ResponseEntity<?> getOneById(Integer ID) {
        BandEntity band = bandRepo.findById(ID).orElse(null);
        if (band == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(band);
        }
    }

    @Override
    public ResponseEntity<?> newOne(BandFillableDTO dto) {
        BandEntity newBand = bandConverter.fromDTOtoEntity(dto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(bandRepo.save(newBand));
        } catch (Exception ex) {
            System.out.println("ERROR: Duplicate name found. The entity already exists.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Override
    public ResponseEntity<?> editOne(BandFillableDTO dto, Integer ID) {
        return bandRepo.findById(ID).map(band -> {
            band = bandConverter.fromDTOtoEntity(dto);
            band.setId(ID);
            return ResponseEntity.ok((bandRepo.save(band)));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer ID) {
        if (bandRepo.findById(ID).orElse(null) != null) {
            bandRepo.deleteById(ID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
