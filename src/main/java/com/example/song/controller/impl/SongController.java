package com.example.song.controller.impl;

import com.example.song.controller.SongAPI;
import com.example.song.controller.dto.SongFillableDTO;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SongController implements SongAPI {
    private final SongService songService;

    @Override
    public ResponseEntity<?> findAll() {
        return songService.findAll();
    }

    @Override
    public ResponseEntity<?> getOneById(Integer id) {
        return songService.getOneById(id);
    }

    @Override
    public ResponseEntity<?> newOne(SongFillableDTO dto) {
        return songService.newOne(dto);
    }

    @Override
    public ResponseEntity<?> editOne(SongFillableDTO dto, Integer id) {
        return songService.editOne(dto, id);
    }

    @Override
    public ResponseEntity<?> deleteOne(Integer id) {
        return songService.deleteOne(id);
    }
}
