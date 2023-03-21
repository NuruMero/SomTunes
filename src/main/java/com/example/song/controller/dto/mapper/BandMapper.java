package com.example.song.controller.dto.mapper;

import com.example.song.controller.dto.BandDto;
import com.example.song.entity.BandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandMapper INSTANCE = Mappers.getMapper(BandMapper.class);

    @Mapping(target = "songs", ignore = true)
    BandEntity toEntity(BandDto dto);

    BandDto toDto(BandEntity entity);
}
