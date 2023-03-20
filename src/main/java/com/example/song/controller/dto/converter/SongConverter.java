package com.example.song.controller.dto.converter;

import com.example.song.controller.dto.SongDto;
import com.example.song.entity.SongEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongConverter {
    private final ModelMapper modelMapper;

    public SongEntity fromDTOtoEntity(SongDto dto) {
        return modelMapper.map(dto, SongEntity.class);
    }

    public SongDto fromEntiytoDTO(SongEntity entity) {
        return modelMapper.map(entity, SongDto.class);
    }

}
