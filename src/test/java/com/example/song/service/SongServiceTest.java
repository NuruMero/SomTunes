package com.example.song.service;

import com.example.song.controller.dto.SongDto;
import com.example.song.controller.dto.mapper.SongMapper;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.impl.SongServiceImpl;
import com.example.song.utils.objectmothers.BandEntityMother;
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
        List<SongEntity> expectedList = SongEntityMother.returnList();
        List<SongDto> expectedDtoList = SongDtoMother.returnList();

        Mockito.when(mapper.toDtoList(expectedList))
                .thenReturn(expectedDtoList);
        Mockito.when(songRepo.findByBand(1))
                .thenReturn(expectedList);

        final List<SongDto> result = songService.getBandSongs(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedDtoList, result);
        Mockito.verify(songRepo).findByBand(1);
        Mockito.verify(mapper).toDtoList(expectedList);
    }

    @Test
    void shouldReturnNewCreateOneTest() {
        SongDto expectedDto = SongDtoMother.returnOne();
        SongDto expectedDtoWithId = SongDtoMother.returnOne();
        expectedDtoWithId.setId(1);

        SongEntity expectedEntity = SongEntityMother.returnOne();
        SongEntity expectedEntityWithId = SongEntityMother.returnOne();
        expectedEntityWithId.setId(1);

        BandEntity expectedBandWithId = BandEntityMother.returnOne();
        expectedBandWithId.setId(1);

        Mockito.when(bandRepo.findById(expectedDto.getBand()))
                .thenReturn(Optional.of(expectedBandWithId));
        Mockito.when(mapper.toEntity(expectedDto))
                .thenReturn(expectedEntity);
        Mockito.when(songRepo.save(expectedEntity))
                .thenReturn(expectedEntityWithId);
        Mockito.when(mapper.toDto(expectedEntityWithId))
                .thenReturn(expectedDtoWithId);

        final SongDto result = songService.createOne(expectedDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, expectedDtoWithId);
        Mockito.verify(bandRepo).findById(expectedDto.getBand());
        Mockito.verify(mapper).toEntity(expectedDto);
        Mockito.verify(songRepo).save(expectedEntity);
        Mockito.verify(mapper).toDto(expectedEntityWithId);
    }

    @Test
    void shouldReturnEditOneTest() {
        SongDto expectedDto = SongDtoMother.returnOne();
        SongDto expectedExistingDto = SongDtoMother.returnOne();
        expectedExistingDto.setId(1);

        SongEntity expectedEntity = SongEntityMother.returnOne();
        SongEntity expectedExistingEntity = SongEntityMother.returnOne();
        expectedExistingEntity.setId(1);

        BandEntity expectedBandWithId = BandEntityMother.returnOne();
        expectedBandWithId.setId(1);
        expectedExistingEntity.setBand(expectedBandWithId);

        Mockito.when(songRepo.findById(1))
                .thenReturn(Optional.of(expectedExistingEntity));
        Mockito.when(bandRepo.findById(expectedDto.getBand()))
                .thenReturn(Optional.of(expectedBandWithId));
        Mockito.when(mapper.toEntity(expectedDto))
                .thenReturn(expectedEntity);
        Mockito.when(songRepo.save(expectedEntity))
                .thenReturn(expectedExistingEntity);
        Mockito.when(mapper.toDto(expectedExistingEntity))
                .thenReturn(expectedExistingDto);

        final SongDto result = songService.editOne(expectedDto, 1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, expectedExistingDto);
        Mockito.verify(songRepo).findById(1);
        Mockito.verify(bandRepo).findById(expectedDto.getBand());
        Mockito.verify(mapper).toEntity(expectedDto);
        Mockito.verify(songRepo).save(expectedEntity);
        Mockito.verify(mapper).toDto(expectedExistingEntity);
    }

    @Test
    void shouldReturnDeleteOneTest() {
        SongEntity expectedEntity = SongEntityMother.returnOne();

        Mockito.when(songRepo.findById(1))
                .thenReturn(Optional.of(expectedEntity));

        final boolean result = songService.deleteOne(1);

        Assertions.assertTrue(result);
        Mockito.verify(songRepo).findById(1);
        Mockito.verify(songRepo).deleteById(1);
    }
}