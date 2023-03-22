package com.example.song.service;

import com.example.song.controller.dto.BandDto;
import com.example.song.controller.dto.mapper.BandMapper;
import com.example.song.entity.BandEntity;
import com.example.song.repository.BandRepository;
import com.example.song.repository.SongRepository;
import com.example.song.service.impl.BandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

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

    public BandDto setUpDto1() {
        BandDto dto = new BandDto();
        dto.setId(1);
        dto.setName("Rhapsody of Fire");
        dto.setOrigin("Thundercross");
        Set<String> members = new HashSet<>();
        members.add("Luca Turilli");
        members.add("Alex Staropoli");
        members.add("Roberto De Micheli");
        members.add("Alessandro Sala");
        members.add("Giacomo Voli");
        members.add("Paolo Marchesich");
        members.add("Fabio Lione (former)");
        dto.setMembers(members);
        dto.setMainGenre("Power Metal");
        return dto;
    }

    public BandDto setUpDto2() {
        BandDto dto = new BandDto();
        dto.setName("Rammstein");
        dto.setOrigin("Berlin, Germany");
        Set<String> members = new HashSet<>();
        members.add("Till Lindemann");
        members.add("Richard Z. Kruspe");
        members.add("Christoph Schneider");
        dto.setMembers(members);
        dto.setMainGenre("Industrial Metal");
        return dto;
    }

    @Test
    void getOneById() {
        /**
        BandDto dto = setUpDto1();
        BandEntity expectedEntity = mapper.toEntity(dto);
        ResponseEntity<BandDto> expectedRE = ResponseEntity.ok(dto);

        Mockito.when(bandRepo.findById(1))
                .thenReturn(1);

        final ResponseEntity<?> result = bandService.getOneById(1);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedRE, result);
        Mockito.verify(bandRepo).findById(1);
         **/
    }

    @Test
    void createOne() {
        BandDto dto = setUpDto1();
        BandEntity expectedEntity = mapper.toEntity(dto);
        ResponseEntity<BandDto> expectedRE = ResponseEntity.status(HttpStatus.CREATED).body(dto);

        Mockito.when(bandRepo.save(expectedEntity))
                .thenReturn(expectedEntity);

        final ResponseEntity<?> result = bandService.createOne(dto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedRE, result);
        Mockito.verify(bandRepo).save(expectedEntity);
    }

    @Test
    void editOne() {
    }

    @Test
    void deleteOne() {
    }
}