package com.example.song.repository.impl;

import com.example.song.controller.dto.filters.FilterBandDto;
import com.example.song.entity.BandEntity;
import com.example.song.repository.custom.BandRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz de peticiones personalizadas de bandas.
 */
@RequiredArgsConstructor
public class BandRepositoryImpl implements BandRepositoryCustom {

    private final EntityManager entityManager;

    /**
     * Devuelve una lista de bandas según el objeto de filtrado.
     * @param filter
     * @return
     */
    @Override
    public List<BandEntity> filterBands(FilterBandDto filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<BandEntity> criteriaQuery = criteriaBuilder.createQuery(BandEntity.class);
        Root<BandEntity> root = criteriaQuery.from(BandEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+filter.getName()+"%"));
        }
        if (filter.getMainGenre() != null && !filter.getMainGenre().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("mainGenre"), "%"+filter.getMainGenre()+"%"));
        }
        if (filter.getOrigin() != null && !filter.getOrigin().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("origin"), "%"+filter.getOrigin()+"%"));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

        Query<BandEntity> query = (Query<BandEntity>) entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
