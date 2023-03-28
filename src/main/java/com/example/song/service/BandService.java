package com.example.song.service;

import com.example.song.controller.dto.BandDto;
import com.example.song.exception.DuplicatedUniqueObjectException;

import java.util.List;

public interface BandService {
    public List<BandDto> findAll();

    public BandDto getOneById(Integer ID);

    public BandDto createOne(BandDto dto) throws DuplicatedUniqueObjectException;

    public BandDto editOne(BandDto dto, Integer ID) throws DuplicatedUniqueObjectException;

    public boolean deleteOne(Integer ID);
}
