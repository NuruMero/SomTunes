package com.example.song.controller.impl;

import com.example.song.controller.SongAPI;
import com.example.song.controller.dto.SongDto;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
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
    public ResponseEntity<?> getOneById(Integer id) {
        SongDto dto = songService.getOneById(id);
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
    public ResponseEntity<?> createOne(SongDto dto) {
        SongDto result = songService.createOne(dto);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
    }

    @Override
    public ResponseEntity<?> editOne(SongDto dto, Integer id) {
        SongDto result = songService.editOne(dto, id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer id) {
        boolean result = songService.deleteOne(id);
        if (!result) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
