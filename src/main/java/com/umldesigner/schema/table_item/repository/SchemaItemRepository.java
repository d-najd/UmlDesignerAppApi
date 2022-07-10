package com.umldesigner.schema.table_item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.table_item.domain.SchemaItem;

@Repository
public interface SchemaItemRepository extends JpaRepository<SchemaItem, Integer> {
    public Optional<SchemaItem> findByUuid(String uuid);

    public List<SchemaItem> findAllByTableUuid(String tUuid); //I am genuinely surprised this works
}