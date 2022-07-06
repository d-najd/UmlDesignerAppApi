package com.umldesigner.schema.template.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.template.domain.TItem;

@Repository
public interface TRepository extends JpaRepository<TItem, Integer> {
    public Optional<TItem> findById(Integer id);
}