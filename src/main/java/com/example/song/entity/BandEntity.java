package com.example.song.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name="Band")
@Table(name="Band")
public class BandEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    @NotBlank(message = "Band name is required")
    private String name;
    @Column
    private String mainGenre;
    @Column
    private Set<String> members;
    @Column
    private String origin;

    @OneToMany(mappedBy = "band",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<SongEntity> songs = new HashSet<>();
}
