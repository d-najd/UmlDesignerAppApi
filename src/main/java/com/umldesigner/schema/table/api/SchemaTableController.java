package com.umldesigner.schema.table.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.schema.table.dto.SchemaTablePojo;
import com.umldesigner.schema.table.service.SchemaTableService;

@RestController
@RequestMapping("/schema/table")
public class SchemaTableController {

    @Autowired
    SchemaTableService schemaTableService;

    @GetMapping("/{id}")
    public SchemaTablePojo getByTablePojo(@PathVariable(value = "id") Integer id) {
        return schemaTableService.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public SchemaTablePojo creaTablePojo(@RequestBody SchemaTablePojo schemaTablePojo){
        return schemaTableService.createTable(schemaTablePojo); 
    } 
}