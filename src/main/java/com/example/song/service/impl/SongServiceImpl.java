package com.example.song.service.impl;

import com.example.song.controller.dto.SongDto;
import com.example.song.controller.dto.filters.FilterSongDto;
import com.example.song.controller.dto.mapper.SongMapper;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Implementación de SongService.
 */
@RequiredArgsConstructor
@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepo;
    private final BandRepository bandRepo;
    private final SongMapper mapper;

    @Override
    public List<SongDto> findAll() {
        return mapper.toDtoList(songRepo.findAll());
    }

    @Override
    public SongDto getById(Integer ID) {
        return mapper.toDto(songRepo.findById(ID).orElse(null));
    }

    @Override
    public List<SongDto> getBandSongs(Integer ID) {
        return mapper.toDtoList(songRepo.findByBand(ID));
    }

    @Override
    public List<SongDto> filterSongs(
            String name,
            String genre,
            Date release_min,
            Date release_max,
            Float length,
            String lyrics,
            Integer band
    ) {
        return mapper.toDtoList(songRepo.filterSongs(new FilterSongDto(name, genre, release_min, release_max, length, lyrics, band)));
    }

    @Override
    public SongDto create(SongDto dto) {
        if (dto.getBand() == null) {
            return null;
        }

        BandEntity band = bandRepo.findById(dto.getBand()).orElse(null);
        if (band != null) {
            SongEntity newSong = mapper.toEntity(dto);
            newSong.setBand(band);
            return mapper.toDto(songRepo.save(newSong));
        } else {
            return null;
        }
    }

    @Override
    public SongDto edit(SongDto dto, Integer ID) {
        return mapper.toDto(songRepo.findById(ID).map(song -> {
            BandEntity band = bandRepo.findById(dto.getBand()).orElse(null);
            if (band == null) {
                return null;
            }
            song = mapper.toEntity(dto);
            song.setBand(band);
            song.setId(ID);
            return songRepo.save(song);
        }).orElse(null));
    }

    @Override
    public boolean delete(Integer ID) {
        if (songRepo.findById(ID).orElse(null) != null) {
            songRepo.deleteById(ID);
            return true;
        } else {
            return false;
        }
    }
}
