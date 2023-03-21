package com.example.song.service.impl;

import com.example.song.controller.dto.SongDto;
import com.example.song.controller.dto.converter.SongConverter;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepo;
    private final BandRepository bandRepo;
    private final SongConverter songConverter;

    @Override
    public ResponseEntity<?> findAll() {
        List<SongEntity> songlist = songRepo.findAll();
        if (songlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(songlist);
        }
    }

    @Override
    public ResponseEntity<?> getOneById(Integer ID) {
        SongEntity song = songRepo.findById(ID).orElse(null);
        if (song == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(song);
        }
    }

    @Override
    public ResponseEntity<?> createOne(SongDto dto) {
        SongEntity newSong = songConverter.fromDTOtoEntity(dto);
        BandEntity band = bandRepo.findById(dto.getBand()).orElse(null);
        if (band != null) {
            band.setId(dto.getBand());
            newSong.setBand(band);
            return ResponseEntity.status(HttpStatus.CREATED).body(songRepo.save(newSong));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: The specified Band doesn't exist in the database!");
        }
    }

    @Override
    public ResponseEntity<?> editOne(SongDto dto, Integer ID) {
        //TODO
        return songRepo.findById(ID).map(song -> {
            BandEntity band = bandRepo.findById(dto.getBand()).orElse(null);
            song = songConverter.fromDTOtoEntity(dto);
            song.setId(ID);
            song.setBand(band);
            return ResponseEntity.ok((songRepo.save(song)));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer ID) {
        //TODO
        if (songRepo.findById(ID).orElse(null) != null) {
            songRepo.deleteById(ID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
