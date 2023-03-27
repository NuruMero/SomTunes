package com.example.song.utils.objectmothers;

import com.example.song.controller.dto.SongDto;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class SongDtoMother {

    public static SongDto returnOne() {
        SongDto dto = new SongDto();
        dto.setGenre("Industrial Metal");
        dto.setName("Zick Zack");
        dto.setLyrics(null);
        dto.setRelease(new Date(2022- 4 - 7));
        dto.setLength(4.04F);
        dto.setBand(1);
        return dto;
    }

    public static SongDto returnOneWithId() {
        SongDto dto = returnOne();
        dto.setId(1);
        return dto;
    }

    public static List<SongDto> returnList() {
        return Collections.singletonList(returnOne());
    }

    public static List<SongDto> returnListWithId() {
        return Collections.singletonList(returnOneWithId());
    }
}
