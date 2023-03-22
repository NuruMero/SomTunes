package com.example.song.service.impl;

import com.example.song.controller.dto.SongDto;
import com.example.song.controller.dto.mapper.SongMapper;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepo;
    private final BandRepository bandRepo;
    private final SongMapper mapper;

    @Override
    public ResponseEntity<?> findAll() {
        List<SongEntity> songlist = songRepo.findAll();
        if (songlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<SongDto> dtoList = new ArrayList<>();
            songlist.forEach(song -> dtoList.add(mapper.toDto(song)));
            return ResponseEntity.ok(dtoList);
        }
    }

    @Override
    public ResponseEntity<?> getOneById(Integer ID) {
        SongEntity song = songRepo.findById(ID).orElse(null);
        if (song == null) {
            return ResponseEntity.notFound().build();
        } else {
            SongDto dto = mapper.toDto(song);
            return ResponseEntity.ok(dto);
        }
    }

    @Override
    public ResponseEntity<?> getBandSongs(Integer ID) {
        List<SongEntity> songlist = songRepo.findByBand(ID);
        if (songlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<SongDto> dtoList = new ArrayList<>();
            songlist.forEach(song -> dtoList.add(mapper.toDto(song)));
            return ResponseEntity.ok(dtoList);
        }
    }

    @Override
    public ResponseEntity<?> createOne(SongDto dto) {
        SongEntity newSong = mapper.toEntity(dto);
        BandEntity band = bandRepo.findById(dto.getBand()).orElse(null);
        if (dto.getBand() == null) {
            songRepo.save(newSong);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
        if (band != null) {
            band.setId(dto.getBand());
            newSong.setBand(band);
            songRepo.save(newSong);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: The specified Band doesn't exist in the database!");
        }
    }

    @Override
    public ResponseEntity<?> editOne(SongDto dto, Integer ID) {
        return songRepo.findById(ID).map(song -> {
            BandEntity band = bandRepo.findById(dto.getBand()).orElse(null);
            song = mapper.toEntity(dto);
            song.setId(ID);
            song.setBand(band);
            songRepo.save(song);
            return ResponseEntity.ok(dto);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer ID) {
        if (songRepo.findById(ID).orElse(null) != null) {
            songRepo.deleteById(ID);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
