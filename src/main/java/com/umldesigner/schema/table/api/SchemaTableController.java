package com.umldesigner.schema.table.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.schema.table.dto.SchemaTablePojo;
import com.umldesigner.schema.table.service.SchemaTableService;

@RestController
@RequestMapping("/schema/table")
public class SchemaTableController {

    @Autowired
    SchemaTableService schemaTableService;

    @GetMapping("/{id}")
    public SchemaTablePojo getByItemPojo(@PathVariable(value = "id") Integer id) {
        return schemaTableService.findById(id);
    }
}