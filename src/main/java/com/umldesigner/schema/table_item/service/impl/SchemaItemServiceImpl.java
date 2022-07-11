package com.umldesigner.schema.table_item.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
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
    public SchemaItemPojo createSchemaItem(String tUuid, SchemaItemPojo schemaItemPojo) {
        return createSchemaItem(tUuid, schemaItemPojo, null);
    }

    @Override
    public SchemaItemPojo createSchemaItem(String tUuid, SchemaItemPojo schemaItemPojo, Integer position) {
        log.debug("Execute createSchemaItem parameters {}, {}", tUuid, schemaItemPojo);
        SchemaTable schemaTable = schemaTableRepository.findByUuid(tUuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaTable with uuid {} is not found", tUuid);
            return new ResourceNotFoundException("Resource SchemaTable not found");
        });
        SchemaItem transientSchemaItem = schemaItemMapper.dtoToEntity(schemaItemPojo);
        transientSchemaItem.setTable(schemaTable);
        // sets the position to a given position if not null if not uses the method
        // given below
        transientSchemaItem
                .setPosition(position != null ? position : schemaItemLogic.getNextPosition(schemaTable.getItems()));
        SchemaItem persistedSchemaItem = schemaItemRepository.save(transientSchemaItem);

        return schemaItemMapper.entityToDto(persistedSchemaItem);
    }

    /**
     * creates given list of items
     */
    @Override
    public List<SchemaItemPojo> createSchemaItemList(String tUuid, List<SchemaItemPojo> schemaItemPojoList) {
        log.debug("Execute createSchemaItemList with parameters {}, {}", tUuid, schemaItemPojoList);

        SchemaTable schemaTable = schemaTableRepository.findByUuid(tUuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaTable with uuid {} is not found", tUuid);
            return new ResourceNotFoundException("Resource SchemaTable not found");
        });
        Integer curBiggestPos = schemaItemLogic.getNextPosition(schemaTable.getItems());
        List<SchemaItemPojo> returnList = new ArrayList<>();
        for (Integer i = 0; i < schemaItemPojoList.size(); i++) {
            returnList.add(createSchemaItem(tUuid, schemaItemPojoList.get(i), i + curBiggestPos));
        }

        return returnList;
    }

    @Override
    public Set<SchemaItemPojo> createSchemaItemSet(String tUuid, Set<SchemaItemPojo> schemaItemPojoSet) {
        /*
         * log.debug("Execute createSchemaItemSet with parameters {}, {}", tUuid,
         * schemaItemPojoSet);
         * Set<SchemaItemPojo> returnSet = new HashSet<>();
         * for (SchemaItemPojo schemaItemPojo : schemaItemPojoSet) {
         * returnSet.add(createSchemaItem(tUuid, schemaItemPojo));
         * }
         * 
         * return returnSet;
         */
        throw new UnsupportedOperationException(
                "if you want to use this implement the iterator design pattern for this and createSchemaItemList and abstract the common stuff");
    }

    @Override
    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo) {
        log.debug("Execute updateSchemaItem with parameters {}, {}", uuid, schemaItemPojo);
        SchemaItem persistedSchemaItem = findByUuid(uuid);
        schemaItemMapper.mapRequestedFieldForUpdate(persistedSchemaItem, schemaItemPojo);

        return schemaItemMapper.entityToDto(schemaItemRepository.saveAndFlush(persistedSchemaItem));
    }

    // TODO implement this
    @Override
    public void swapSchemaItems(String tUuid, String firstUuid, String secondUuid) {
        log.debug("Execute swapSchemaItems with parameters {}, {}, {}", tUuid, firstUuid, secondUuid);
        SchemaTable schemaTable = schemaTableRepository.findByUuid(tUuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaTable with uuid {} not found", tUuid);
            return new ResourceNotFoundException("Unable to find Resource Schema Table");
        });

        // I won't let prettier butcher my boy by putting it in a comment
        final SchemaItem schemaItem = schemaTable.getItems().parallelStream().findAny(o -> o.getUuid.equals(firstUuid))
                .orElseThrow(() -> {
                    log.error("Error: Resource SchemaItem with uuid {} not found in SchemaTable with uuid {}",
                            firstUuid,
                            tUuid);
                    return new ResourceNotFoundException("Unable to find Resource Schema Item");
                });
        throw new UnsupportedOperationException("implement this");
    }

    @Override
    public void removeSchemaItem(String uuid) {
        log.debug("Execute removeSchemaItem with parameter {}", uuid);
        SchemaItem persistedSchemaItem = findByUuid(uuid);
        schemaItemRepository.delete(persistedSchemaItem);
    }
}