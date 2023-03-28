package com.example.song.service;

import com.example.song.controller.dto.BandDto;
import com.example.song.controller.dto.mapper.BandMapper;
import com.example.song.entity.BandEntity;
import com.example.song.entity.SongEntity;
import com.example.song.exception.DuplicatedUniqueObjectException;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.impl.BandServiceImpl;
import com.example.song.utils.objectmothers.BandDtoMother;
import com.example.song.utils.objectmothers.BandEntityMother;
import com.example.song.utils.objectmothers.SongEntityMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class BandServiceTest {

    @Mock
    private BandRepository bandRepo;
    @Mock
    private SongRepository songRepo;
    @Mock
    private BandMapper mapper;

    @InjectMocks
    private BandServiceImpl bandService;

    @Test
    void shouldReturnAllTest() {
        List<BandEntity> expectedList = BandEntityMother.returnListWithId();
        List<BandDto> expectedDtoList = BandDtoMother.returnListWithId();

        Mockito.when(mapper.toDtoList(expectedList))
                .thenReturn(expectedDtoList);
        Mockito.when(bandRepo.findAll())
                .thenReturn(expectedList);

        final List<BandDto> result = bandService.findAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedDtoList, result);
        Mockito.verify(bandRepo).findAll();
        Mockito.verify(mapper).toDtoList(expectedList);
    }

    @Test
    void shouldReturnOneByIdTest() {
        BandEntity expectedEntity = BandEntityMother.returnOneWithId();
        BandDto dto = BandDtoMother.returnOneWithId();

        Mockito.when(mapper.toDto(expectedEntity))
                .thenReturn(dto);
        Mockito.when(bandRepo.findById(1))
                .thenReturn(Optional.of(expectedEntity));

        final BandDto result = bandService.getById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(dto, result);
        Mockito.verify(bandRepo).findById(1);
        Mockito.verify(mapper).toDto(expectedEntity);
    }

    @Test
    void shouldReturnNewCreateOneTest() throws DuplicatedUniqueObjectException {
        BandDto expectedDto = BandDtoMother.returnOne();
        BandDto expectedDtoWithId = BandDtoMother.returnOneWithId();

        BandEntity expectedEntity = BandEntityMother.returnOne();
        BandEntity expectedEntityWithId = BandEntityMother.returnOneWithId();

        Mockito.when(mapper.toEntity(expectedDto))
                .thenReturn(expectedEntity);
        Mockito.when(bandRepo.save(expectedEntity))
                .thenReturn(expectedEntityWithId);
        Mockito.when(mapper.toDto(expectedEntityWithId))
                .thenReturn(expectedDtoWithId);

        final BandDto result = bandService.create(expectedDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedDtoWithId, result);
        Mockito.verify(mapper).toEntity(expectedDto);
        Mockito.verify(bandRepo).save(expectedEntity);
        Mockito.verify(mapper).toDto(expectedEntityWithId);

    }

    @Test
    void shouldReturnEditOneTest() throws DuplicatedUniqueObjectException {
        BandDto expectedDto = BandDtoMother.returnOne();
        BandDto expectedExistingDto = BandDtoMother.returnOneWithId();
        BandEntity expectedEntity = BandEntityMother.returnOne();
        BandEntity expectedExistingEntity = BandEntityMother.returnOneWithId();

        Mockito.when(bandRepo.findById(1))
                .thenReturn(Optional.of(expectedExistingEntity));
        Mockito.when(mapper.toEntity(expectedDto))
                .thenReturn(expectedEntity);
        Mockito.when(bandRepo.save(expectedEntity))
                .thenReturn(expectedEntity);
        Mockito.when(mapper.toDto(expectedExistingEntity))
                .thenReturn(expectedExistingDto);

        final BandDto result = bandService.edit(expectedDto, 1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedExistingDto, result);
        Mockito.verify(bandRepo).findById(1);
        Mockito.verify(mapper).toEntity(expectedDto);
        Mockito.verify(bandRepo).save(expectedEntity);
        Mockito.verify(mapper).toDto(expectedEntity);
    }

    @Test
    void shouldReturnDeleteOneTest() {
        BandEntity expectedEntity = BandEntityMother.returnOne();
        List<SongEntity> songList = SongEntityMother.returnList();

        Mockito.when(bandRepo.findById(1))
                .thenReturn(Optional.of(expectedEntity));
        Mockito.when(songRepo.findByBand(1))
                .thenReturn(songList);

        final boolean result = bandService.delete(1);

        Assertions.assertTrue(result);
        Mockito.verify(bandRepo).findById(1);
        Mockito.verify(songRepo).findByBand(1);
        Mockito.verify(bandRepo).deleteById(1);
    }
}