package com.example.song.service;

import com.example.song.controller.dto.SongDto;
import com.example.song.controller.dto.mapper.SongMapper;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.impl.SongServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;

@ExtendWith(MockitoExtension.class)
class SongServiceTest {

    @Mock
    private SongRepository songRepo;
    @Mock
    private BandRepository bandRepo;
    @Mock
    private SongMapper mapper;

    @InjectMocks
    private SongServiceImpl songService;

    public SongDto setUpDto1() {
        SongDto dto = new SongDto();
        dto.setId(1);
        dto.setGenre("Power Metal");
        dto.setName("Emerald Sword");
        dto.setLyrics(null);
        dto.setRelease(new Date(1998- 1 - 1));
        dto.setLength(4.5F);
        dto.setBand(null);
        return dto;
    }

    @Test
    void findAll() {
    }

    @Test
    void getOneById() {
    }

    @Test
    void getBandSongs() {
    }

    @Test
    void createOne() {
        SongDto dto = setUpDto1();
        SongEntity expectedEntity = mapper.toEntity(dto);
        ResponseEntity<SongDto> expectedRE = ResponseEntity.status(HttpStatus.CREATED).body(dto);

        Mockito.when(songRepo.save(expectedEntity))
                .thenReturn(expectedEntity);

        final ResponseEntity<?> result = songService.createOne(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedRE, result);
        Mockito.verify(songRepo).save(expectedEntity);
    }

    @Test
    void editOne() {
    }

    @Test
    void deleteOne() {
    }
}