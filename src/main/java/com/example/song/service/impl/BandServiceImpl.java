package com.example.song.service.impl;

import com.example.song.controller.dto.BandDto;
import com.example.song.controller.dto.mapper.BandMapper;
import com.example.song.entity.SongEntity;
import com.example.song.exception.DuplicatedUniqueObjectException;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.BandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public BandDto create(BandDto dto) throws DuplicatedUniqueObjectException {
        try {
            return mapper.toDto(bandRepo.save(mapper.toEntity(dto)));
        } catch (Exception ex) {
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
        } catch (Exception ex) {
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
