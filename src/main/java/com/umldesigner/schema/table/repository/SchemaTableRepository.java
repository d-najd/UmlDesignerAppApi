package com.umldesigner.schema.table.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.table.domain.SchemaTable;

@Repository
public interface SchemaTableRepository extends JpaRepository<SchemaTable, Integer> {
	public Optional<SchemaTable> findByUuid(String uuid);
}