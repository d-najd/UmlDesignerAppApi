package com.umldesigner.schema.item.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.exceptions.ResourceNotFoundException;
import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.dto.SchemaItemPojo;
import com.umldesigner.schema.item.mapper.SchemaItemMapper;
import com.umldesigner.schema.item.repository.SchemaItemRepository;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.repository.SchemaTableRepository;

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
		return schemaItemMapper.mapList(schemaItemRepository.findAll(), SchemaItemPojo.class);
    }

    @Override
	public SchemaItemPojo createSchemaItem(SchemaItemPojo schemaItemPojo) {
		log.debug("Execute createItem with parameters {}", schemaItemPojo);
		SchemaItem transientSchemaItem = schemaItemMapper.dtoToEntity(schemaItemPojo);
        transientSchemaItem.setTableId(schemaTableRepository.findById(20).get());

        log.debug("formaafter t {}", transientSchemaItem.getTableId());
		SchemaItem persistedSchemaItem = schemaItemRepository.save(transientSchemaItem);

		return schemaItemMapper.entityToDto(persistedSchemaItem);
	}

    @Override
    public Set<SchemaItemPojo> createSchemaItemSet(Set<SchemaItemPojo> schemaItemPojoSet) {
        log.debug("Execute createItemSet with parameters {}", schemaItemPojoSet);
        Set<SchemaItemPojo> returnSet = new HashSet<>();
        for (SchemaItemPojo schemaItemPojo : schemaItemPojoSet){
            returnSet.add(createSchemaItem(schemaItemPojo));
        }
        log.debug("returnset is {}", returnSet); 
        return returnSet;
    }

    @Override
    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo) {
        log.debug("Execute updateItem with parameters {}", schemaItemPojo);
		SchemaItem persistedSchemaItem = findByUuid(uuid);
		schemaItemMapper.mapRequestedFieldForUpdate(persistedSchemaItem, schemaItemPojo);

		return schemaItemMapper.entityToDto(schemaItemRepository.saveAndFlush(persistedSchemaItem));
    }

    @Override
    public void removeSchemaItem(String uuid) {
	    SchemaItem persistedSchemaItem = findByUuid(uuid);
	    schemaItemRepository.delete(persistedSchemaItem);
    }

}