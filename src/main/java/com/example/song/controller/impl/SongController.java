package com.example.song.controller.impl;

import com.example.song.controller.SongAPI;
import com.example.song.controller.dto.SongDto;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
public class SongController implements SongAPI {
    private final SongService songService;

    @Override
    public ResponseEntity<?> findAll() {
        List<SongDto> songlist = songService.findAll();
        if (songlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(songlist);
        }    }

    @Override
    public ResponseEntity<?> getById(Integer id) {
        SongDto dto = songService.getById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(dto);
        }
    }

    @Override
    public ResponseEntity<?> getBandSongs(Integer id) {
        List<SongDto> songlist = songService.getBandSongs(id);
        if (songlist.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(songlist);
        }
    }

    @Override
    public ResponseEntity<?> filterSongs(
            String name,
            String genre,
            Date release_min,
            Date release_max,
            Float length,
            String lyrics,
            Integer band
    ) {
        List<SongDto> dtoList = songService.filterSongs(name, genre, release_min, release_max, length, lyrics, band);
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<?> create(SongDto dto) {
        SongDto result = songService.create(dto);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
    }

    @Override
    public ResponseEntity<?> edit(SongDto dto, Integer id) {
        SongDto result = songService.edit(dto, id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        boolean result = songService.delete(id);
        if (!result) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
