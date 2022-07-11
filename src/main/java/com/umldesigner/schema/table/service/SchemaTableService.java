package com.umldesigner.schema.table.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.dto.SchemaTablePojo;

@Service
public interface SchemaTableService {

    public SchemaTablePojo findById(Integer id);

    public SchemaTablePojo getByUuid(String uuid);

    public SchemaTable findByUuid(String uuid);

    public List<SchemaTablePojo> getAll();

    public SchemaTablePojo createSchemaTable(SchemaTablePojo schemaTablePojo);

    public SchemaTablePojo updateSchemaTable(String uuid, SchemaTablePojo schemaTablePojo);

    public void removeSchemaTable(String uuid);

}