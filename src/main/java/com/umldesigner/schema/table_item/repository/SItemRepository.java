package com.umldesigner.schema.table_item.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.umldesigner.schema.table_item.domain.SItem;

@Repository
public interface SItemRepository extends JpaRepository<SItem, Integer> {

    public Optional<SItem> findByUuid(String uuid);

    public List<SItem> findAllByTableUuid(String tUuid); // I am genuinely surprised this works

    public Optional<SItem> findByTableUuidAndUuid(String tUuid, String uuid); // spring just keeps on givin
}