package com.example.song.service;

import com.example.song.controller.dto.SongDto;

import java.sql.Date;
import java.util.List;

/**
 * Interfaz de lógica principal para Canciones.
 * Contiene el CRUD básico y un método de filtrado.
 */
public interface SongService {
    public List<SongDto> findAll();

    public SongDto getById(Integer ID);

    public List<SongDto> getBandSongs(Integer ID);

    public List<SongDto> filterSongs(
            String name,
            String genre,
            Date release_min,
            Date release_max,
            Float length,
            String lyrics,
            Integer band
    );

    public SongDto create(SongDto dto);

    public SongDto edit(SongDto dto, Integer ID);

    public boolean delete(Integer ID);
}
