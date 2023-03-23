package com.example.song.service;

import com.example.song.controller.dto.SongDto;
import com.example.song.controller.dto.mapper.SongMapper;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.impl.SongServiceImpl;
import com.example.song.utils.objectmothers.SongDtoMother;
import com.example.song.utils.objectmothers.SongEntityMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldReturnAllTest() {
        List<SongEntity> expectedList = SongEntityMother.returnList();
        List<SongDto> expectedDtoList = SongDtoMother.returnList();

        Mockito.when(mapper.toDtoList(expectedList))
                .thenReturn(expectedDtoList);
        Mockito.when(songRepo.findAll())
                .thenReturn(expectedList);

        final List<SongDto> result = songService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedDtoList, result);
        Mockito.verify(songRepo).findAll();
        Mockito.verify(mapper).toDtoList(expectedList);
    }

    @Test
    void shouldReturnOneByIdTest() {
        SongEntity expectedEntity = SongEntityMother.returnOne();
        SongDto dto = SongDtoMother.returnOne();

        Mockito.when(mapper.toDto(expectedEntity))
                .thenReturn(dto);
        Mockito.when(songRepo.findById(1))
                .thenReturn(Optional.of(expectedEntity));

        final SongDto result = songService.getOneById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto, result);
        Mockito.verify(songRepo).findById(1);
        Mockito.verify(mapper).toDto(expectedEntity);
    }

    @Test
    void shouldReturnAllSongsOfABandTest() {
    }

    @Test
    void shouldReturnNewCreateOneTest() {
        /**
         *         SongDto dto = setUpDto1();
         *         SongEntity expectedEntity = mapper.toEntity(dto);
         *         ResponseEntity<SongDto> expectedRE = ResponseEntity.status(HttpStatus.CREATED).body(dto);
         *
         *         Mockito.when(songRepo.save(expectedEntity))
         *                 .thenReturn(expectedEntity);
         *
         *         final ResponseEntity<?> result = songService.createOne(dto);
         *
         *         Assertions.assertNotNull(result);
         *         Assertions.assertEquals(expectedRE, result);
         *         Mockito.verify(songRepo).save(expectedEntity);
         */
    }

    @Test
    void shouldReturnEditOneTest() {
    }

    @Test
    void shouldReturnDeleteOneTest() {
    }
}