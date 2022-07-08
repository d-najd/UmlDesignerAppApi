package com.umldesigner.schema.table.service;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.table.dto.SchemaTablePojo;

@Service
public interface SchemaTableService {
    public SchemaTablePojo findById(Integer id);
    
    public SchemaTablePojo createTable(SchemaTablePojo schemaTablePojo);

    public SchemaTablePojo removeTable(SchemaTablePojo schemaTablePojo);
}