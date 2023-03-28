package com.example.song.controller;

import com.example.song.controller.dto.BandDto;
import com.example.song.exception.DuplicatedUniqueObjectException;
import com.example.song.utils.EndpointUrls;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(EndpointUrls.Base + EndpointUrls.Bands)
public interface BandAPI {

    //GET HTTP requests
    @GetMapping
    public ResponseEntity<?> findAll();

    @GetMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> getOneById(@PathVariable Integer id);

    //POST HTTP requests
    @PostMapping
    public ResponseEntity<?> createOne(@RequestBody BandDto dto) throws DuplicatedUniqueObjectException;

    //PUT HTTP requests
    @PutMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> editOne(@RequestBody BandDto dto, @PathVariable Integer id) throws DuplicatedUniqueObjectException;

    //DELETE HTTP requests
    @DeleteMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> deleteOne(@PathVariable Integer id);
}
