package com.example.song.controller;

import com.example.song.controller.dto.BandDto;
import com.example.song.exception.DuplicatedUniqueObjectException;
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

/**
 * Interfaz de manejo de peticiones para Bandas.
 */
@CrossOrigin
@RequestMapping(EndpointUrls.Base + EndpointUrls.Bands)
@Tag(name = "Band API", description = "Band API REST with CRUD and search methods")
public interface BandAPI {

    //GET HTTP requests
    @Operation(summary = "findAll Bands", description = "Retrieves all bands from the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieved all bands", content = {
                @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = BandDto.class)))}),

        @ApiResponse(responseCode = "404", description = "Retrieved no bands")
    })
    @GetMapping
    public ResponseEntity<?> findAll();

    @Operation(summary = "getById Bands", description = "Retrieves one band of a specific id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved band", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BandDto.class))}),

            @ApiResponse(responseCode = "404", description = "Didn't find band of that id")
    })
    @GetMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> getById(@PathVariable Integer id);

    @Operation(summary = "getByName Bands", description = "Retrieves one band of a specific name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved band", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BandDto.class))}),

            @ApiResponse(responseCode = "404", description = "Didn't find any band with that name")
    })
    @GetMapping(EndpointUrls.requiresText)
    public ResponseEntity<?> getByName(@PathVariable String name);

    @Operation(summary = "filterBands", description = "Retrieves all bands, filtered by the terms included")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieved filtered bands", content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BandDto.class)))})
    })
    @GetMapping(EndpointUrls.search)
    public ResponseEntity<?> filterBands(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String mainGenre,
            @RequestParam(required = false) String origin
    );

    //POST HTTP requests
    @Operation(summary = "create Bands", description = "Creates a band")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Band created successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BandDto.class))})
    })
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody BandDto dto) throws DuplicatedUniqueObjectException;

    //PUT HTTP requests
    @Operation(summary = "edit Bands", description = "Edits a band")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Band edited successfully", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BandDto.class))}),

            @ApiResponse(responseCode = "404", description = "Didn't find any band with that id")
    })
    @PutMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> edit(@Valid @RequestBody BandDto dto, @PathVariable Integer id) throws DuplicatedUniqueObjectException;

    //DELETE HTTP requests
    @Operation(summary = "delete Bands", description = "Deletes a band")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Band deleted successfully"),

            @ApiResponse(responseCode = "404", description = "Didn't find any band with that id")
    })
    @DeleteMapping(EndpointUrls.requiresID)
    public ResponseEntity<?> delete(@PathVariable Integer id);
}
