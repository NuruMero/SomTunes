package com.example.song.service;

import com.example.song.controller.dto.SongDto;

import java.util.List;

public interface SongService {

    public List<SongDto> findAll();

    public SongDto getById(Integer ID);

    public List<SongDto> getBandSongs(Integer ID);

    public SongDto create(SongDto dto);

    public SongDto edit(SongDto dto, Integer ID);

    public boolean delete(Integer ID);
}
