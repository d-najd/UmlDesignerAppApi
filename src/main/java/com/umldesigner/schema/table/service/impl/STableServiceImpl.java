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
import com.umldesigner.schema.table_item.service.SItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class STableServiceImpl implements STableService {

    @Autowired
    STableRepository sTableRepository;

    @Autowired
    STableMapper sTableMapper;

    @Autowired
    SItemService sItemService;

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

    /*
     * creating a table which may include items, note this may cause security
     * problems but I don't know of a better way of doing this atm
     * 
     * @see
     * com.umldesigner.schema.table.service.SchemaTableService#createSchemaTable(com
     * .umldesigner.schema.table.dto.SchemaTablePojo)
     */
    @Override
    public STablePojo createSchemaTable(STablePojo sTablePojo) {
        log.debug("Execute createSchemaTable with parameters {}", sTablePojo);
        STable transientSTable = sTableMapper.dtoToEntity(sTablePojo);
        STable persistentSTable = sTableRepository.save(transientSTable);

        // creating and setting the items to the persisted table
        STablePojo mappedSchemaTable = sTableMapper.entityToDto(persistentSTable);
        mappedSchemaTable.setItems(
                sItemService.createSchemaItemList(persistentSTable.getUuid(), sTablePojo.getItems()));

        return mappedSchemaTable;
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