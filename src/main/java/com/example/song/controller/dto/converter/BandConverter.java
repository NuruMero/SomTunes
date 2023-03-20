package com.example.song.controller.dto.converter;

import com.example.song.controller.dto.BandFillableDTO;
import com.example.song.entity.BandEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BandConverter {
    private final ModelMapper modelMapper;

    public BandEntity fromDTOtoEntity(BandFillableDTO dto) {
        return modelMapper.map(dto, BandEntity.class);
    }

    public BandFillableDTO fromEntiytoDTO(BandEntity entity) {
        return modelMapper.map(entity, BandFillableDTO.class);
    }
}
