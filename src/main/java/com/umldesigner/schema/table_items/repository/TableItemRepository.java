package com.umldesigner.schema.table_items.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.table_items.domain.TableItem;

@Repository
public interface TableItemRepository extends JpaRepository<TableItem, BaseMTMIdentity> {
    public Optional<TableItem> findById(BaseMTMIdentity identity);
}