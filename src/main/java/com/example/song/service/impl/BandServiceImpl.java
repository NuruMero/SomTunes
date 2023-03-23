package com.example.song.service.impl;

import com.example.song.controller.dto.BandDto;
import com.example.song.controller.dto.mapper.BandMapper;
import com.example.song.entity.SongEntity;
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
    public BandDto getOneById(Integer ID) {
        return mapper.toDto(bandRepo.findById(ID).orElse(null));
    }

    @Override
    public BandDto createOne(BandDto dto) {
        try {
            return mapper.toDto(bandRepo.save(mapper.toEntity(dto)));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public BandDto editOne(BandDto dto, Integer ID) {
        return mapper.toDto(bandRepo.findById(ID).map(band -> {
            band = mapper.toEntity(dto);
            band.setId(ID);
            bandRepo.save(band);
            return band;
        }).orElse(null));
    }

    @Override
    public boolean deleteOne(Integer ID) {
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
