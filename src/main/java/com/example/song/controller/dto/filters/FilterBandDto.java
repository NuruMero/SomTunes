package com.example.song.controller.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para el filtrado de Bandas en búsqueda por parámetros.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterBandDto {
    private String name;
    private String mainGenre;
    private String origin;
}
