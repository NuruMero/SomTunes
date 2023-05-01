package com.example.song.controller.dto;

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

    private Integer id;
    private String name;
    private String mainGenre;
    private Set<String> members;
    private String origin;
}
