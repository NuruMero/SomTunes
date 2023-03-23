package com.example.song.utils.objectmothers;

import com.example.song.controller.dto.SongDto;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class SongDtoMother {

    public static SongDto returnOne() {
        SongDto dto = new SongDto();
        dto.setId(1);
        dto.setGenre("Power Metal");
        dto.setName("Emerald Sword");
        dto.setLyrics(null);
        dto.setRelease(new Date(1998- 1 - 1));
        dto.setLength(4.5F);
        dto.setBand(null);
        return dto;
    }

    public static List<SongDto> returnList() {
        return Collections.singletonList(returnOne());
    }
}
