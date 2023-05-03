package com.example.song.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Data Transfer Object de Bandas.
 * Se utilizará para evitar filtrar información de las Entities.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandDto {

    @Schema(example = "1", description= "Identifier, PK for Band")
    private Integer id;
    @Schema(example = "Metallica", description= "Band name")
    private String name;
    @Schema(example = "Heavy Metal", description= "Most common genre for the Band")
    private String mainGenre;
    @Schema(example = "[\"Lars Ulrich\", \"James Hetfield\", \"Kirk Hammett\", \"Robert Trujillo\"]", description= "List of members")
    private Set<String> members;
    @Schema(example = "L.A., California, United States", description= "Previous band name or place of origin")
    private String origin;
}
