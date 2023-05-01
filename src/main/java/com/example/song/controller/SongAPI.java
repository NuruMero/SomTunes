package com.example.song.controller;

import com.example.song.controller.dto.SongDto;
import com.example.song.utils.EndpointUrls;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Interfaz de manejo de peticiones para Canciones.
 */
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

    @GetMapping(EndpointUrls.search)
    public ResponseEntity<?> filterSongs(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Date release_min,
            @RequestParam(required = false) Date release_max,
            @RequestParam(required = false) Float length,
            @RequestParam(required = false) String lyrics,
            @RequestParam(required = false) Integer band
            );

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
