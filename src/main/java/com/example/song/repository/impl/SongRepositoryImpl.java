package com.example.song.repository.impl;

import com.example.song.controller.dto.filters.FilterSongDto;
import com.example.song.entity.SongEntity;
import com.example.song.repository.custom.SongRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SongRepositoryImpl implements SongRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<SongEntity> filterSongs(FilterSongDto filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SongEntity> criteriaQuery = criteriaBuilder.createQuery(SongEntity.class);
        Root<SongEntity> root = criteriaQuery.from(SongEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%"+filter.getName()+"%"));
        }
        if (filter.getGenre() != null && !filter.getGenre().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("genre"), "%"+filter.getGenre()+"%"));
        }
        if (filter.getRelease_min() != null) {
            if (filter.getRelease_max() != null) {
                predicates.add(criteriaBuilder.between(root.get("release"), filter.getRelease_min(), filter.getRelease_max()));
            } else {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("release"), filter.getRelease_min()));
            }
        } else if (filter.getRelease_max() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("release"), filter.getRelease_max()));
        }
        if (filter.getLength() != null && !filter.getLength().isNaN()) {
            predicates.add(criteriaBuilder.ge(root.get("length"), filter.getLength()));
        }
        if (filter.getLyrics() != null && !filter.getLyrics().isEmpty()) {
            predicates.add(criteriaBuilder.like(root.get("lyrics"), "%"+filter.getLyrics()+"%"));
        }
        if (filter.getBand() != null) {
            predicates.add(criteriaBuilder.equal(root.get("band"), filter.getBand()));
        }

        criteriaQuery.select(root).where(predicates.toArray(new Predicate[0]));

        Query<SongEntity> query = (Query<SongEntity>) entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
