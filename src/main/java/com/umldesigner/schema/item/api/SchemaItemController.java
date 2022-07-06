package com.umldesigner.schema.item.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.schema.item.dto.SchemaItemPojo;
import com.umldesigner.schema.item.service.SchemaItemService;

@RestController
@RequestMapping("/schema/table/item")
public class SchemaItemController {

    @Autowired
    SchemaItemService schemaItemService;

    @GetMapping("/{id}")
    public SchemaItemPojo getByItemPojo(@PathVariable(value = "id") Integer id) {
        return schemaItemService.findById(id);
    }
}