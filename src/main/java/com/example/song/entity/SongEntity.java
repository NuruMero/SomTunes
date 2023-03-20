package com.example.song.entity;

import lombok.Data;

import jakarta.persistence.*;

import java.sql.Date;

@Data
@Entity(name="Song")
@Table(name="Song")
public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String name;
    @Column
    private String genre;
    @Column
    private Date release;
    @Column
    private Float length;
    @Column
    private String lyrics;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="band")
    private BandEntity band;
}
