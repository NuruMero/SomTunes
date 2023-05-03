package com.example.song.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Data Transfer Object de Canciones.
 * Se utilizará para evitar filtrar información de las Entities.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {

    @Schema(example = "1", description= "Identifier, PK for Song")
    private Integer id;
    @Schema(example = "For Whom The Bell Tolls", description= "Song name")
    private String name;
    @Schema(example = "Heavy Metal", description= "Song genre")
    private String genre;
    @Schema(example = "1984-01-01", description= "Date of release")
    private Date release;
    @Schema(example = "5.10", description= "Song length in minutes")
    private Float length;
    @Schema(example = "For whom the bell tolls\n" +
            "Time marches on\n" +
            "For whom the bell tolls\n", description= "Excerpt or full lyrics")
    private String lyrics;

    @Schema(example = "1", description= "ID of the band this song belongs to")
    private Integer band; //Band id
}