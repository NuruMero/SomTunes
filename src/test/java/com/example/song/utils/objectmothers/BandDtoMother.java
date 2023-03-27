package com.example.song.utils.objectmothers;

import com.example.song.controller.dto.BandDto;

import java.util.*;

public class BandDtoMother {

    public static BandDto returnOne() {
        BandDto dto = new BandDto();
        dto.setName("Rammstein");
        dto.setOrigin("Berlin, Germany");
        Set<String> members = new HashSet<>();
        members.add("Till Lindemann");
        members.add("Richard Z. Kruspe");
        members.add("Christoph Schneider");
        dto.setMembers(members);
        dto.setMainGenre("Industrial Metal");
        return dto;
    }

    public static BandDto returnOneWithId() {
        BandDto dto = returnOne();
        dto.setId(1);
        return dto;
    }

    public static List<BandDto> returnList() {
        return Collections.singletonList(returnOne());
    }

    public static List<BandDto> returnListWithId() {
        return Collections.singletonList(returnOneWithId());
    }

}
