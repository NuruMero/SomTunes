package com.example.song.utils.objectmothers;

import com.example.song.entity.BandEntity;

import java.util.*;

public class BandEntityMother {

    public static BandEntity returnOne() {
        BandEntity band = new BandEntity();
        band.setName("Rammstein");
        band.setOrigin("Berlin, Germany");
        Set<String> members = new HashSet<>();
        members.add("Till Lindemann");
        members.add("Richard Z. Kruspe");
        members.add("Christoph Schneider");
        band.setMembers(members);
        band.setMainGenre("Industrial Metal");
        return band;
    }

    public static BandEntity returnOneWithId() {
        BandEntity band = returnOne();
        band.setId(1);
        return band;
    }

    public static List<BandEntity> returnList() {
        return Collections.singletonList(returnOne());
    }

    public static List<BandEntity> returnListWithId() {
        return Collections.singletonList(returnOneWithId());
    }
}
