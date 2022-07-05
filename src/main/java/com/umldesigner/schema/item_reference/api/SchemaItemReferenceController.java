package com.umldesigner.schema.item_reference.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.item_reference.dto.SchemaItemReferencePojo;
import com.umldesigner.schema.item_reference.service.SchemaItemReferenceService;

@RestController
@RequestMapping("/schema/item/reference")
public class SchemaItemReferenceController {

    @Autowired
    SchemaItemReferenceService schemaItemReferenceService;

    @GetMapping("/get")
    public SchemaItemReferencePojo getByItemPojo(@RequestBody BaseMTMIdentity identity) {
        return schemaItemReferenceService.findById(identity);
    }
}