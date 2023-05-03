package com.example.song.controller;

import com.example.song.controller.dto.SongDto;
import com.example.song.utils.EndpointUrls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Interfaz de manejo de peticiones para Canciones.
 */
@CrossOrigin
@RequestMapping(EndpointUrls.Base + EndpointUrls.Songs)
@Tag(name = "Song API", description = "Song API REST with CRUD and search methods")
public interface SongAPI {

    //GET HTTP requests
    @Operation(summary = "findAll Songs", description = "Retrieves all songs from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all songs", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDto.class)))}),

            @ApiResponse(responseCode = "404", description = "Retrieved no songs")
    })
    @GetMapping
    public ResponseEntity<?> findAll();

    @Operation(summary = "getById Songs", description = "Retrieves one song of a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved song", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDto.class))}),

            @ApiResponse(responseCode = "404", description = "Didn't find song of that id")
    })
    @GetMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> getById(@PathVariable Integer id);

    @Operation(summary = "getBandSongs", description = "Retrieves all songs from a certain band")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved all songs of a band", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDto.class)))}),

            @ApiResponse(responseCode = "404", description = "Retrieved no songs")
    })
    @GetMapping(EndpointUrls.MadeBy + EndpointUrls.requiresID)
    public ResponseEntity<?> getBandSongs(@PathVariable Integer id);

    @Operation(summary = "filterSongs", description = "Retrieves all songs, filtered by the terms included")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved filtered songs", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SongDto.class)))})
    })
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
    @Operation(summary = "create Songs", description = "Creates a Song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Song created successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDto.class))}),

            @ApiResponse(responseCode = "404", description = "Didn't find any song whose band's ID matched with that id")
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody SongDto dto);

    //PUT HTTP requests
    @Operation(summary = "edit Songs", description = "Edits a Song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Song edited successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SongDto.class))}),

            @ApiResponse(responseCode = "404", description = "Didn't find any song with that id")
    })
    @PutMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> edit(@Valid @RequestBody SongDto dto, @PathVariable Integer id);

    //DELETE HTTP requests
    @Operation(summary = "delete Songs", description = "Deletes a song")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Song deleted successfully"),

            @ApiResponse(responseCode = "404", description = "Didn't find any song with that id")
    })
    @DeleteMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> delete(@PathVariable Integer id);
}
