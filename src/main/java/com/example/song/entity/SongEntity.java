package com.example.song.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

/**
 * Clase de Entidad para Canciones.
 *
 * Define las entidades de la base de datos a través de la anotación @Entity.
 * Tiene como obligatorios los atributos de 'id' y 'name'.
 * Una canción puede pertenecer a una sola banda.
 */
@Data
@Entity(name="Song")
@Table(name="Song")
public class SongEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    @NotBlank(message = "Song name is required")
    private String name;
    @Column
    private String genre;
    @Column
    private Date release;
    @Column
    private Float length;
    @Column
    private String lyrics;

    @ManyToOne(fetch = FetchType.LAZY)
    private BandEntity band;
}
