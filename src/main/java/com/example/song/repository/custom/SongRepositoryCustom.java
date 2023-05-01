package com.example.song.repository.custom;

import com.example.song.controller.dto.filters.FilterSongDto;
import com.example.song.entity.SongEntity;

import java.util.List;

/**
 * Interfaz de repositorio para peticiones personalizadas de Canciones.
 */
public interface SongRepositoryCustom {

    /**
     * Devuelve una lista de canciones según el objeto de filtrado.
     * @param filter
     * @return
     */
    List<SongEntity> filterSongs(FilterSongDto filter);
}
