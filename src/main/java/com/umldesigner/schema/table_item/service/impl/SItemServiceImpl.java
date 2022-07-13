package com.umldesigner.schema.table_item.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.service.STableService;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;
import com.umldesigner.schema.table_item.logic.SItemLogic;
import com.umldesigner.schema.table_item.mapper.SItemMapper;
import com.umldesigner.schema.table_item.repository.SItemRepository;
import com.umldesigner.schema.table_item.service.SItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SItemServiceImpl implements SItemService {

    @Autowired
    SItemRepository sItemRepository;

    @Autowired
    SItemMapper sItemMapper;

    @Autowired
    SItemLogic sItemLogic;

    @Autowired
    STableService sTableService;

    @Override
    public SItemPojo findById(Integer id) {
        log.debug("Execute findById with parameter {}", id);
        SItem schemaItemEntity = sItemRepository.findById(id).orElseThrow(() -> {
            log.error("Resource SchemaItem with id {} is not found", id);
            return new ResourceNotFoundException("Resource SchemaItem not found");
        });

        return sItemMapper.entityToDto(schemaItemEntity);
    }

    @Override
    public SItemPojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

        return sItemMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public SItem findByUuid(String uuid) {
        log.debug("Execute findByUuid with parameter {}", uuid);

        return sItemRepository.findByUuid(uuid).orElseThrow(() -> {
            log.error("Error: Resource SchemaItem with uuid {} is not found", uuid);
            return new ResourceNotFoundException("Resource SchemaItem not found");
        });
    }

    @Override
    public List<SItemPojo> getAll() {
        log.debug("Execute getAll");

        return sItemMapper.mapList(sItemRepository.findAll(), SItemPojo.class);
    }

    @Override
    public List<SItemPojo> getAllByTableUuid(String tUuid) {
        log.debug("Execute getAllByTableUuid with parameter {}", tUuid);

        return sItemMapper.mapList(sItemRepository.findAllByTableUuid(tUuid), SItemPojo.class);
    }

    @Override
    public SItemPojo createSchemaItem(String tUuid, SItemPojo sItemPojo) {
        return createSchemaItem(tUuid, sItemPojo, null);
    }

    @Override
    public SItemPojo createSchemaItem(String tUuid, SItemPojo sItemPojo, Integer position) {
        log.debug("Execute createSchemaItem parameters {}, {}", tUuid, sItemPojo);
        STable sTable = sTableService.findByUuid(tUuid);
        SItem transientSchemaItem = sItemMapper.dtoToEntity(sItemPojo);
        transientSchemaItem.setTable(sTable);
        // sets the position to a given position if not null if not uses the method
        // given below
        transientSchemaItem
                .setPosition(position != null ? position : sItemLogic.getNextPosition(sTable.getItems()));
        SItem persistedSItem = sItemRepository.save(transientSchemaItem);

        return sItemMapper.entityToDto(persistedSItem);
    }

    @Override
    public List<SItemPojo> createSchemaItemList(String tUuid, List<SItemPojo> sItemPojoList) {
        log.debug("Execute createSchemaItemList with parameters {}, {}", tUuid, sItemPojoList);

        STable sTable = sTableService.findByUuid(tUuid);
        Integer curBiggestPos = sItemLogic.getNextPosition(sTable.getItems());
        List<SItemPojo> returnList = new ArrayList<>();
        for (Integer i = 0; i < sItemPojoList.size(); i++) {
            returnList.add(createSchemaItem(tUuid, sItemPojoList.get(i), i + curBiggestPos));
        }

        return returnList;
    }

    @Override
    public Set<SItemPojo> createSchemaItemSet(String tUuid, Set<SItemPojo> sItemPojoSet) {
        /*
         * log.debug("Execute createSchemaItemSet with parameters {}, {}", tUuid,
         * sItemPojoSet);
         * Set<SchemaItemPojo> returnSet = new HashSet<>();
         * for (SchemaItemPojo sItemPojo : sItemPojoSet) {
         * returnSet.add(createSchemaItem(tUuid, sItemPojo));
         * }
         * 
         * return returnSet;
         */
        throw new UnsupportedOperationException(
                "if you want to use this implement the iterator design pattern for this and createSchemaItemList and abstract the common stuff");
    }

    @Override
    public SItemPojo updateSchemaItem(String uuid, SItemPojo sItemPojo) {
        log.debug("Execute updateSchemaItem with parameters {}, {}", uuid, sItemPojo);
        SItem persistedSItem = findByUuid(uuid);
        sItemMapper.mapRequestedFieldForUpdate(persistedSItem, sItemPojo);

        return sItemMapper.entityToDto(sItemRepository.saveAndFlush(persistedSItem));
    }

    @Override
    public void swapSchemaItems(String tUuid, String fUuid, String sUuid) {
        log.debug("Execute swapSchemaItems with parameters {}, {}, {}", tUuid, fUuid, sUuid);

        // getting items
        SItem fItem = sItemRepository.findByTableUuidAndUuid(tUuid, fUuid).orElseThrow(() -> {
            log.error("Error: Resource Schema Item with uuid {} and Table uuid {} not found", fUuid, tUuid);
            return new ResourceNotFoundException("Unable to find Resource Schema Item");
        });

        SItem sItem = sItemRepository.findByTableUuidAndUuid(tUuid, sUuid).orElseThrow(() -> {
            log.error("Error: Resource Schema Item with uuid {} and Table uuid {} not found", sUuid, tUuid);
            return new ResourceNotFoundException("Unable to find Resource Schema Item");
        });

        // swaping the positions
        int sItemPos = sItem.getPosition();
        sItem.setPosition(fItem.getPosition());
        fItem.setPosition(sItemPos);

        sItemRepository.save(fItem);
        sItemRepository.save(sItem);
    }

    @Override
    public void removeSchemaItem(String uuid) {
        log.debug("Execute removeSchemaItem with parameter {}", uuid);
        SItem persistedSItem = findByUuid(uuid);
        sItemRepository.delete(persistedSItem);
    }
}