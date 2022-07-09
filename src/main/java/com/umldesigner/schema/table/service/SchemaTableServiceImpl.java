package com.umldesigner.schema.table.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.exceptions.ResourceNotFoundException;
import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.service.SchemaItemService;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.dto.SchemaTablePojo;
import com.umldesigner.schema.table.mapper.SchemaTableMapper;
import com.umldesigner.schema.table.repository.SchemaTableRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SchemaTableServiceImpl implements SchemaTableService {
    @Autowired
    SchemaTableRepository schemaTableRepository;

    @Autowired
    SchemaTableMapper schemaTableMapper;

    @Autowired
    SchemaItemService schemaItemService;

    @Override
    public SchemaTablePojo findById(Integer id) {
        log.debug("Execute findById with parameter {}", id);
            SchemaTable schemaTableEntity = schemaTableRepository.findById(id).orElseThrow(() -> {
                log.error("Resource SchemaTable with id {} is not found", id);
			    return new ResourceNotFoundException("Resource SchemaTable not found");
            });

        return schemaTableMapper.entityToDto(schemaTableEntity);
    }

    @Override
    public SchemaTablePojo getByUuid(String uuid) {
        log.debug("Execute getByUuid with parameter {}", uuid);

		return schemaTableMapper.entityToDto(findByUuid(uuid));
    }

    @Override
    public SchemaTable findByUuid(String uuid) {
		log.debug("Execute findByUuid with parameter {}", uuid);
		return schemaTableRepository.findByUuid(uuid).orElseThrow(() -> {
			log.error("Error: Resource SchemaTable with uuid {} is not found", uuid);
			return new ResourceNotFoundException("Resource SchemaTable not found");
		});
    }

    @Override
    public List<SchemaTablePojo> getAll() {
		return schemaTableMapper.mapList(schemaTableRepository.findAll(), SchemaTablePojo.class);
    }

    @Override
	public SchemaTablePojo createSchemaTable(SchemaTablePojo schemaTablePojo) {
		log.debug("Execute createTable with parameters {}", schemaTablePojo);


  		SchemaTable transientSchemaTable = schemaTableMapper.dtoToEntity(schemaTablePojo);

        //schemaItemService.createSchemaItemSet(schemaItemPojoSet)
        SchemaTable persistedSchemaTable = schemaTableRepository.save(transientSchemaTable);
        
		return schemaTableMapper.entityToDto(persistedSchemaTable);
	}

    @Override
    public SchemaTablePojo updateSchemaTable(String uuid, SchemaTablePojo schemaTablePojo) {
        log.debug("Execute updateTable with parameters {}", schemaTablePojo);
		SchemaTable persistedSchemaTable = findByUuid(uuid);
		schemaTableMapper.mapRequestedFieldForUpdate(persistedSchemaTable, schemaTablePojo);
        
		return schemaTableMapper.entityToDto(schemaTableRepository.saveAndFlush(persistedSchemaTable));
    }

    @Override
    public void removeSchemaTable(String uuid) {
	    SchemaTable persistedSchemaTable = findByUuid(uuid);
	    schemaTableRepository.delete(persistedSchemaTable);
    }
}