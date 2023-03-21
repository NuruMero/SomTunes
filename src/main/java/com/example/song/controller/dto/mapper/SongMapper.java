package com.example.song.controller.dto.mapper;

import com.example.song.controller.dto.SongDto;
import com.example.song.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "band", ignore = true)
    SongEntity toEntity(SongDto dto);

    @Mapping(target = "band", ignore = true)
    SongDto toDto(SongEntity entity);
}
