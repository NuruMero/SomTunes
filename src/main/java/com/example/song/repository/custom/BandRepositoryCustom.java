package com.example.song.repository.custom;

import com.example.song.controller.dto.filters.FilterBandDto;
import com.example.song.entity.BandEntity;

import java.util.List;

/**
 * Interfaz de repositorio para peticiones personalizadas de Bandas.
 */
public interface BandRepositoryCustom {

    /**
     * Devuelve una lista de bandas según el objeto de filtrado.
     * @param filter
     * @return
     */
    List<BandEntity> filterBands(FilterBandDto filter);

}
