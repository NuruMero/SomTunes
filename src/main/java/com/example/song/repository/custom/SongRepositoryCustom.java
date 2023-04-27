package com.example.song.repository.custom;

import com.example.song.controller.dto.filters.FilterSongDto;
import com.example.song.entity.SongEntity;

import java.util.List;

public interface SongRepositoryCustom {

    List<SongEntity> filterSongs(FilterSongDto filter);
}
