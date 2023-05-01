package com.example.song.repository;

import com.example.song.entity.BandEntity;
import com.example.song.repository.custom.BandRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz de extensión de JpaRepository y las funciones del filtrado para la entidad Banda.
 */
@Repository
public interface BandRepository extends JpaRepository<BandEntity, Integer>, BandRepositoryCustom {

    /**
     * Busca bandas de nombre igual al introducido.
     * @param name
     * @return
     */
    BandEntity findByName(String name);

}