package com.example.song.controller;

import com.example.song.controller.dto.SongDto;
import com.example.song.utils.EndpointUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(EndpointUrls.Base + EndpointUrls.Songs)
public interface SongAPI {

    //GET HTTP requests
    @GetMapping
    public ResponseEntity<?> findAll();

    @GetMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> getById(@Validated @PathVariable Integer id);

    @GetMapping(EndpointUrls.MadeBy + EndpointUrls.requiresID)
    public ResponseEntity<?> getBandSongs(@Validated @PathVariable Integer id);

    //POST HTTP requests
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody SongDto dto);

    //PUT HTTP requests
    @PutMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> edit(@Validated @RequestBody SongDto dto, @Validated @PathVariable Integer id);

    //DELETE HTTP requests
    @DeleteMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> delete(@Validated @PathVariable Integer id);
}
