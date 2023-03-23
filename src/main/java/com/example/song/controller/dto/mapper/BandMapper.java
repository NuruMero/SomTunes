package com.example.song.controller.dto.mapper;

import com.example.song.controller.dto.BandDto;
import com.example.song.entity.BandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandMapper INSTANCE = Mappers.getMapper(BandMapper.class);

    @Mapping(target = "songs", ignore = true)
    BandEntity toEntity(BandDto dto);

    @Mapping(target = "songs", ignore = true)
    List<BandEntity> toEntityList(List<BandDto> listDto);

    BandDto toDto(BandEntity entity);

    List<BandDto> toDtoList(List<BandEntity> entityList);
}
