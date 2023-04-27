package com.example.song.controller.dto.filters;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterSongDto {
    private String name;
    private String genre;
    private Date release_min; //Between min and max
    private Date release_max;
    private Float length; //Longer than length
    private String lyrics;
    private Integer band; //Band id
}
