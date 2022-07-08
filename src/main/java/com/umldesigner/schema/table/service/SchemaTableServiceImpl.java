package com.umldesigner.schema.table.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

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

    @Override
    public SchemaTablePojo findById(Integer id) {
        log.debug("Execute getByUuid with parameter {}", id);
        try {
            SchemaTable schemaTableEntity = schemaTableRepository.findById(id).orElseThrow(NotFoundException::new);
            return schemaTableMapper.entityToDto(schemaTableEntity);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
	public SchemaTablePojo createTable(SchemaTablePojo schemaTablePojo) {
		log.debug("Execute createBook with parameters ", schemaTablePojo);
		SchemaTable transientSchemaTable = schemaTableMapper.dtoToEntity(schemaTablePojo);
		SchemaTable persistedSchemaTable = schemaTableRepository.save(transientSchemaTable);
		return schemaTableMapper.entityToDto(persistedSchemaTable);
	}

    @Override
    public SchemaTablePojo removeTable(SchemaTablePojo schemaTablePojo) {
        
        return null;
    }
}