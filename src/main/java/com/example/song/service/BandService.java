package com.example.song.service;

import com.example.song.controller.dto.BandDto;

import java.util.List;

public interface BandService {
    public List<BandDto> findAll();

    public BandDto getOneById(Integer ID);

    public BandDto createOne(BandDto dto);

    public BandDto editOne(BandDto dto, Integer ID);

    public boolean deleteOne(Integer ID);
}
