package com.example.song.controller;

import com.example.song.controller.dto.SongDto;
import com.example.song.utils.EndpointUrls;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(EndpointUrls.Base + EndpointUrls.Songs)
public interface SongAPI {

    //GET HTTP requests
    @GetMapping
    public ResponseEntity<?> findAll();

    @GetMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> getById(@PathVariable Integer id);

    @GetMapping(EndpointUrls.MadeBy + EndpointUrls.requiresID)
    public ResponseEntity<?> getBandSongs(@PathVariable Integer id);

    //POST HTTP requests
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SongDto dto);

    //PUT HTTP requests
    @PutMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> edit(@Valid @RequestBody SongDto dto, @PathVariable Integer id);

    //DELETE HTTP requests
    @DeleteMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> delete(@PathVariable Integer id);
}
