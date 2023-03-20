package com.example.song.controller.dto.converter;

import com.example.song.controller.dto.BandDto;
import com.example.song.entity.BandEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandConverter {
    private final ModelMapper modelMapper;

    public BandEntity fromDTOtoEntity(BandDto dto) {
        return modelMapper.map(dto, BandEntity.class);
    }

    public BandDto fromEntiytoDTO(BandEntity entity) {
        return modelMapper.map(entity, BandDto.class);
    }
}
