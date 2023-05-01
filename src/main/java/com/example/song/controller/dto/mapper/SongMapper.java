package com.example.song.controller.dto.mapper;

import com.example.song.controller.dto.SongDto;
import com.example.song.entity.SongEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Interfaz de MapStruct para crear mappers que pasen Entities de Canciones a DTOs.
 */
@Mapper(componentModel = "spring")
public interface SongMapper {
    SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    @Mapping(target = "band", ignore = true)
    SongEntity toEntity(SongDto dto);

    @Mapping(target = "band", ignore = true)
    List<SongEntity> toEntityList(List<SongDto> listDto);

    @Mapping(target = "band", source = "band.id")
    SongDto toDto(SongEntity entity);

    @Mapping(target = "band", source = "band.id")
    List<SongDto> toDtoList(List<SongEntity> entityList);
}
