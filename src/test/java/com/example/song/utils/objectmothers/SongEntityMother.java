package com.example.song.utils.objectmothers;

import com.example.song.entity.SongEntity;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class SongEntityMother {

    public static SongEntity returnOne() {
        SongEntity entity = new SongEntity();
        entity.setGenre("Industrial Metal");
        entity.setName("Zick Zack");
        entity.setLyrics(null);
        entity.setRelease(new Date(2022- 4 - 7));
        entity.setLength(4.04F);
        entity.setBand(BandEntityMother.returnOne());
        return entity;
    }

    public static SongEntity returnOneWithId() {
        SongEntity entity = returnOne();
        entity.setId(1);
        return entity;
    }

    public static List<SongEntity> returnList() {
        return Collections.singletonList(returnOne());
    }

    public static List<SongEntity> returnListWithId() {
        return Collections.singletonList(returnOneWithId());
    }
}
