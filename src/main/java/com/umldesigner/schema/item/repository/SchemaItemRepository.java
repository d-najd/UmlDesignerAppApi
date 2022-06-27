package com.umldesigner.schema.item.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.item.domain.SchemaItem;

@Repository
public interface SchemaItemRepository extends JpaRepository<SchemaItem, Integer> {
    public Optional<SchemaItem> findById(Integer id);
}