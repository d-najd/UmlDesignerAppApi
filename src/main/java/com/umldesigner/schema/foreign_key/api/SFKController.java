package com.umldesigner.schema.foreign_key.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.infrastructure.Endpoints;
import com.umldesigner.schema.foreign_key.service.SFKService;

import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(Endpoints.ITEM_FK)
public class SFKController {

    @Autowired
    SFKService sfkService;

    /**
     * gets Schema Item Foreign Key with inputs as Uuid of the first and second item
     * 
     * @param fUuid Uuid of the first item
     * @param sUuid Uuid of the second item
     * @return Foreign Key Pojo
     */
    @GetMapping("/{fUuid}/{sUuid}")
    public SFKPojo getByIdentity(
            @PathVariable(value = "fUuid") String fUuid,
            @PathVariable(value = "sUuid") String sUuid) {
        return sfkService.getById(fUuid, sUuid);
    }

    @GetMapping
    public List<SFKPojo> getAll() {
        return sfkService.getAll();
    }

    @PostMapping("/{fUuid}/{sUuid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SFKPojo createSchemaForeignKey(
            @RequestBody SFKPojo requestSfkPojo,
            @PathVariable(value = "fUuid") String fUuid,
            @PathVariable(value = "sUuid") String sUuid) {
        return sfkService.createForeignKey(fUuid, sUuid, requestSfkPojo);
    }

    @PutMapping("/{fUuid}/{sUuid}")
    @ResponseStatus(value = HttpStatus.OK)
    public SFKPojo updateSchemaForeignKey(
            @RequestBody SFKPojo requestSfkPojo,
            @PathVariable(value = "fUuid") String fUuid,
            @PathVariable(value = "sUuid") String sUuid) {
        return sfkService.updateForeignKey(fUuid, sUuid, requestSfkPojo);
    }
}