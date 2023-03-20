package com.example.song.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.util.Set;

@Data
@Entity(name="Band")
@Table(name="Band")
public class BandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String name;
    @Column
    private String mainGenre;
    @Column
    private Set<String> members;
    @Column
    private String origin;

    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL)
    private Set<SongEntity> songs;
}
