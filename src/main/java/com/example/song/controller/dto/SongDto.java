package com.example.song.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {

    private Integer id;
    private String name;
    private String genre;
    private Date release;
    private Float length;
    private String lyrics;

    private Integer band; //Band id
}