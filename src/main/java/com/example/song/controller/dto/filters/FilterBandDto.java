package com.example.song.controller.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterBandDto {
    private String name;
    private String mainGenre;
    private String origin;
}
