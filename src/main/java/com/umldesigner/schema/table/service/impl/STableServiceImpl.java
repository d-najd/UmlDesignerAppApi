package com.umldesigner.schema.table.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.mapper.STableMapper;
import com.umldesigner.schema.table.repository.STableRepository;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table_item.domain.SItem;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class STableServiceImpl implements STableService {

    @Autowired
    STableRepository sTableRepository;

    @Autowired
    STableMapper sTableMapper;

    @Override
    public STablePojo findById(Integer id) {
        log.debug("Execute findById with parameter {}", id);
        STable schemaTableEntity = sTableRepository.findById(id).orElseThrow(() -> {
            log.error("Resource SchemaTable with id {} is not found", id);
            return new ResourceNotFoundException("Resource SchemaTable not found");
        });

        return sTableMapper.entityToDto(schemaTableEntity);
    }

    @Override
    public STablePojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return sTableMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public STable findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);

        return sTableRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaTable with uuid {} is not found", uuid);
            return new ResourceNotFoundException("Resource SchemaTable not found");
        });
    }

    @Override
    public List<STablePojo> getAll() {
        log.debug("Execute getAll");

        return sTableMapper.mapList(sTableRepository.findAll(), STablePojo.class);
    }

    @Override
    public STablePojo createSchemaTable(STablePojo sTablePojo) {
        log.debug("Execute createSchemaTable with parameters {}", sTablePojo);
        STable transientSTable = sTableMapper.dtoToEntity(sTablePojo);

        // saving the table without the items
        List<SItem> b = transientSTable.getItems();
        transientSTable.setItems(null);
        STable persistentSTable = sTableRepository.save(transientSTable);

        // setting the table to the items since they need it to be created and saving
        // them both
        // NOTE this is probably a terrible way of doing things need to find "more
        // elegant" way later
        for (SItem item : b) {
            item.setTable(persistentSTable);
        }
        persistentSTable.setItems(b);
        persistentSTable = sTableRepository.save(transientSTable);

        return sTableMapper.entityToDto(persistentSTable);
    }

    /**
     * @apiNote doesn't update the table items just the table itself
     */

    @Override
    public STablePojo updateSchemaTable(String uuid, STablePojo sTablePojo) {
        log.debug("Execute updateSchemaTable with parameters {}, {}", uuid, sTablePojo);
        STable persistentSTable = findByUuid(uuid);
        sTableMapper.mapRequestedFieldForUpdate(persistentSTable, sTablePojo);

        return sTableMapper.entityToDto(sTableRepository.saveAndFlush(persistentSTable));
    }

    @Override
    public void removeSchemaTable(String uuid) {
        log.debug("Execute removeSchemaTable with parameter {}", uuid);
        STable persistentSTable = findByUuid(uuid);
        sTableRepository.delete(persistentSTable);
    }
}