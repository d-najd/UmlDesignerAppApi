package com.umldesigner.schema.foreign_key.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.schema.foreign_key.dto.SFKPojo;
import com.umldesigner.schema.foreign_key.service.SFKService;

@RestController
@RequestMapping("/s/item/foreignKey/")
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
    @GetMapping("/{firstUuId}/{secondUuId}")
    public SFKPojo getByIdentity(
            @RequestParam(value = "firstUuid") String fUuid,
            @RequestParam(value = "secondUuid") String sUuid) {
        return sfkService.findById(fUuid, sUuid);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public SFKPojo createSchemaForeignKey(@RequestBody SFKPojo requestsfkPojo) {
        throw new UnsupportedOperationException("Add da method");
    }
}