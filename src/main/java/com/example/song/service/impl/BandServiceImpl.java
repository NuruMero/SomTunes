package com.example.song.service.impl;

import com.example.song.controller.dto.BandDto;
import com.example.song.controller.dto.filters.FilterBandDto;
import com.example.song.controller.dto.mapper.BandMapper;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.exception.DuplicatedUniqueObjectException;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BandServiceImpl implements BandService {
    private final BandRepository bandRepo;
    private final SongRepository songRepo;
    private final BandMapper mapper;

    @Override
    public List<BandDto> findAll() {
        return mapper.toDtoList(bandRepo.findAll());
    }

    @Override
    public BandDto getById(Integer ID) {
        return mapper.toDto(bandRepo.findById(ID).orElse(null));
    }

    @Override
    public BandDto getByName(String name) {
        BandEntity entity = bandRepo.findByName(name);
        if(Objects.isNull(entity)) {
            return new BandDto();
        }
        return mapper.toDto(entity);
    }

    @Override
    public List<BandDto> filterBands(
            String name,
            String mainGenre,
            String origin
    ) {
        return mapper.toDtoList(bandRepo.filterBands(new FilterBandDto(name, mainGenre, origin)));
    }

    @Override
    public BandDto create(BandDto dto) throws DuplicatedUniqueObjectException {
        try {
            return mapper.toDto(bandRepo.save(mapper.toEntity(dto)));
        } catch (NonTransientDataAccessException ex) {
            throw new DuplicatedUniqueObjectException("Duplicate name found in creation. The entity already exists.", ex, 1);
        }
    }

    @Override
    public BandDto edit(BandDto dto, Integer ID) throws DuplicatedUniqueObjectException {
        try {
            return mapper.toDto(bandRepo.findById(ID).map(band -> {
                band = mapper.toEntity(dto);
                band.setId(ID);
                bandRepo.save(band);
                return band;
            }).orElse(null));
        } catch (NonTransientDataAccessException ex) {
            throw new DuplicatedUniqueObjectException("Duplicate name found in update. The entity already exists.", ex, 2);
        }
    }

    @Override
    public boolean delete(Integer ID) {
        if (bandRepo.findById(ID).orElse(null) != null) {
            List<SongEntity> songlist = songRepo.findByBand(ID);
            if (!songlist.isEmpty()) {
                songlist.forEach(songRepo::delete);
            }
            bandRepo.deleteById(ID);
            return true;
        } else {
            return false;
        }
    }
}
