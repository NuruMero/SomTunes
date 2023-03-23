package com.example.song.service;

import com.example.song.controller.dto.SongDto;

import java.util.List;

public interface SongService {

    public List<SongDto> findAll();

    public SongDto getOneById(Integer ID);

    public List<SongDto> getBandSongs(Integer ID);

    public SongDto createOne(SongDto dto);

    public SongDto editOne(SongDto dto, Integer ID);

    public boolean deleteOne(Integer ID);
}
