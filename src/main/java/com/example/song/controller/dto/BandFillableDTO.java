package com.example.song.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BandFillableDTO {

    private String name;
    private String mainGenre;
    private Set<String> members;
    private String origin;
}
