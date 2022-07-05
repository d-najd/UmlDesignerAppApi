package com.umldesigner.schema.item_reference.service;

import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.item_reference.dto.SchemaItemReferencePojo;
import com.umldesigner.schema.table.dto.SchemaTablePojo;

@Service
public interface SchemaItemReferenceService {
   public SchemaItemReferencePojo findById(BaseMTMIdentity identity); 
}
