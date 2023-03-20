package com.example.song.controller.dto.converter;

import com.example.song.controller.dto.SongFillableDTO;
import com.example.song.entity.SongEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongConverter {
    private final ModelMapper modelMapper;

    public SongEntity fromDTOtoEntity(SongFillableDTO dto) {
        return modelMapper.map(dto, SongEntity.class);
    }

    public SongFillableDTO fromEntiytoDTO(SongEntity entity) {
        return modelMapper.map(entity, SongFillableDTO.class);
    }

}
