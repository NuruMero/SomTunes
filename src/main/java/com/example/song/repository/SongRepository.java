package com.example.song.repository;

import com.example.song.entity.SongEntity;
import com.example.song.repository.custom.SongRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interfaz de extensión de JpaRepository y las funciones del filtrado para la entidad Canción.
 */
@Repository
public interface SongRepository extends JpaRepository<SongEntity, Integer>, SongRepositoryCustom {

    /**
     * Busca canciones que pertenezcan a la banda cuyo id se introduzca.
     * @param ID
     * @return
     */
    @Query(value = "SELECT * FROM song s WHERE s.band_id = :id", nativeQuery = true)
    List<SongEntity> findByBand(@Param("id") Integer ID);
}
