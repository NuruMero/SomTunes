package com.example.song.repository.custom;

import com.example.song.controller.dto.filters.FilterBandDto;
import com.example.song.entity.BandEntity;

import java.util.List;

public interface BandRepositoryCustom {

    List<BandEntity> filterBands(FilterBandDto filter);

}
