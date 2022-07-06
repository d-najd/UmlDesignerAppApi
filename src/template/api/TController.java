package com.umldesigner.schema.template.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.schema.template.dto.TPojo;
import com.umldesigner.schema.template.service.TService;

@RestController
@RequestMapping("/template")
public class TController {

    @Autowired
    TService tService;

    @GetMapping("/{id}")
    public TPojo getByItemPojo(@PathVariable(value = "id") Integer id) {
        return tService.findById(id);
    }
}