package com.umldesigner.schema.table_item.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.infrastructure.domain.BaseMTMIdentity;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table_item.domain.SchemaTableItem;

@Repository //this should work?
public interface SchemaTableItemRepository extends JpaRepository<SchemaTableItem, BaseMTMIdentity> {
    //public Optional<SchemaTable> findById(Integer id);
}
