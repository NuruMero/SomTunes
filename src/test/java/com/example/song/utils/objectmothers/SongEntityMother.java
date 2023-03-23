package com.example.song.utils.objectmothers;

import com.example.song.entity.SongEntity;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class SongEntityMother {

    public static SongEntity returnOne() {
        SongEntity entity = new SongEntity();
        entity.setId(1);
        entity.setGenre("Power Metal");
        entity.setName("Emerald Sword");
        entity.setLyrics(null);
        entity.setRelease(new Date(1998- 1 - 1));
        entity.setLength(4.5F);
        entity.setBand(null);
        return entity;
    }

    public static List<SongEntity> returnList() {
        return Collections.singletonList(returnOne());
    }
}
