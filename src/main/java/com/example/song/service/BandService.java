package com.example.song.service;

import com.example.song.controller.dto.BandDto;
import com.example.song.exception.DuplicatedUniqueObjectException;

import java.util.List;

public interface BandService {
    public List<BandDto> findAll();

    public BandDto getById(Integer ID);

    public BandDto getByName(String name);

    public List<BandDto> filterBands(
            String name,
            String mainGenre,
            String origin
    );

    public BandDto create(BandDto dto) throws DuplicatedUniqueObjectException;

    public BandDto edit(BandDto dto, Integer ID) throws DuplicatedUniqueObjectException;

    public boolean delete(Integer ID);
}
