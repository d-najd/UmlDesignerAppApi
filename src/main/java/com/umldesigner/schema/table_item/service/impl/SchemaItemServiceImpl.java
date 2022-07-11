package com.umldesigner.schema.table_item.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.exceptions.ResourceNotFoundException;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.repository.SchemaTableRepository;
import com.umldesigner.schema.table_item.domain.SchemaItem;
import com.umldesigner.schema.table_item.dto.SchemaItemPojo;
import com.umldesigner.schema.table_item.logic.SchemaItemLogic;
import com.umldesigner.schema.table_item.mapper.SchemaItemMapper;
import com.umldesigner.schema.table_item.repository.SchemaItemRepository;
import com.umldesigner.schema.table_item.service.SchemaItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SchemaItemServiceImpl implements SchemaItemService {

    @Autowired
    SchemaItemRepository schemaItemRepository;

    @Autowired
    SchemaItemMapper schemaItemMapper;

    @Autowired
    SchemaItemLogic schemaItemLogic;

    @Autowired
    SchemaTableRepository schemaTableRepository;

    @Override
    public SchemaItemPojo findById(Integer id) {
        log.debug("Execute findById with parameter {}", id);
        SchemaItem schemaItemEntity = schemaItemRepository.findById(id).orElseThrow(() -> {
            log.error("Resource SchemaItem with id {} is not found", id);
            return new ResourceNotFoundException("Resource SchemaItem not found");
        });

        return schemaItemMapper.entityToDto(schemaItemEntity);
    }

    @Override
    public SchemaItemPojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return schemaItemMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public SchemaItem findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);

        return schemaItemRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaItem with uuid {} is not found", uuid);
            return new ResourceNotFoundException("Resource SchemaItem not found");
        });
    }

    @Override
    public List<SchemaItemPojo> getAll() {
        log.debug("Execute getAll");

        return schemaItemMapper.mapList(schemaItemRepository.findAll(), SchemaItemPojo.class);
    }

    @Override
    public List<SchemaItemPojo> getAllByTableUuid(String tUuid) {
        log.debug("Execute getAllByTableUuid with parameter {}", tUuid);

        return schemaItemMapper.mapList(schemaItemRepository.findAllByTableUuid(tUuid), SchemaItemPojo.class);
    }

    @Override
    public void removeSchemaItem(String uuid) {
        log.debug("Execute removeSchemaItem with parameter {}", uuid);
        SchemaItem persistedSchemaItem = findByUuid(uuid);
        schemaItemRepository.delete(persistedSchemaItem);
    }

    @Override
    public SchemaItemPojo createSchemaItem(String tUuid, SchemaItemPojo schemaItemPojo) {
        log.debug("Execute createSchemaItem parameters {}, {}", tUuid, schemaItemPojo);
        SchemaTable schemaTable = schemaTableRepository.findByUuid(tUuid).get();
        SchemaItem transientSchemaItem = schemaItemMapper.dtoToEntity(schemaItemPojo);
        transientSchemaItem.setTable(schemaTable);
        transientSchemaItem.setPosition(schemaItemLogic.getNextPosition(schemaTable.getItems()));
        SchemaItem persistedSchemaItem = schemaItemRepository.save(transientSchemaItem);

        return schemaItemMapper.entityToDto(persistedSchemaItem);
    }

    @Override
    public List<SchemaItemPojo> createSchemaItemList(String tUuid, List<SchemaItemPojo> schemaItemPojoList) {
        log.debug("Execute createSchemaItemList with parameters {}, {}", tUuid, schemaItemPojoList);
        List<SchemaItemPojo> returnList = new ArrayList<>();
        for (SchemaItemPojo schemaItemPojo : schemaItemPojoList) {
            returnList.add(createSchemaItem(tUuid, schemaItemPojo));
        }

        return returnList;
    }

    @Override
    public Set<SchemaItemPojo> createSchemaItemSet(String tUuid, Set<SchemaItemPojo> schemaItemPojoSet) {
        log.debug("Execute createSchemaItemSet with parameters {}, {}", tUuid, schemaItemPojoSet);
        Set<SchemaItemPojo> returnSet = new HashSet<>();
        for (SchemaItemPojo schemaItemPojo : schemaItemPojoSet) { // optimization, is possible to optimize this by
                                                                  // storing the schemaTable instead of searching for it
                                                                  // for every item
            returnSet.add(createSchemaItem(tUuid, schemaItemPojo));
        }

        return returnSet;
    }

    @Override
    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo) {
        log.debug("Execute updateSchemaItem with parameters {}, {}", uuid, schemaItemPojo);
        SchemaItem persistedSchemaItem = findByUuid(uuid);
        schemaItemMapper.mapRequestedFieldForUpdate(persistedSchemaItem, schemaItemPojo);

        return schemaItemMapper.entityToDto(schemaItemRepository.saveAndFlush(persistedSchemaItem));
    }
}