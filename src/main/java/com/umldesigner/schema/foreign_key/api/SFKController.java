package com.umldesigner.schema.foreign_key.api;

import java.util.List;

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
    @GetMapping("/{fUuId}/{sUuId}")
    public SFKPojo getByIdentity(
            @RequestParam(value = "fUuid") String fUuid,
            @RequestParam(value = "sUuid") String sUuid) {
        return sfkService.findById(fUuid, sUuid);
    }

    @GetMapping
    public List<SFKPojo> getAll(){
        return sfkService.findAll();
    }

    @PostMapping("/{fUuid}/{sUuid}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public SFKPojo createSchemaForeignKey(
            @RequestBody SFKPojo requestSFKPojo,
            @RequestParam(value = "fUuid") String fUuid,
            @RequestParam(value = "sUUid") String sUuid) {
        return sfkService.createForeignKey(fUuid, sUuid, requestSFKPojo);
    }
}