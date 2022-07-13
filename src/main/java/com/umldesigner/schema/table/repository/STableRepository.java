package com.umldesigner.schema.table.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.table.domain.STable;

@Repository
public interface STableRepository extends JpaRepository<STable, Integer> {
	public Optional<STable> findByUuid(String uuid);
}