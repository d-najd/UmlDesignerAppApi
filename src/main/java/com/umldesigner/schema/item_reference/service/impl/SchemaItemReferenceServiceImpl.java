package com.umldesigner.schema.item_reference.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.item_reference.domain.SchemaItemReference;
import com.umldesigner.schema.item_reference.dto.SchemaItemReferencePojo;
import com.umldesigner.schema.item_reference.mapper.SchemaItemReferenceMapper;
import com.umldesigner.schema.item_reference.repository.SchemaItemReferenceRepository;
import com.umldesigner.schema.item_reference.service.SchemaItemReferenceService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SchemaItemReferenceServiceImpl implements SchemaItemReferenceService {

    @Autowired
    SchemaItemReferenceRepository schemaItemReferenceRepository;

    @Autowired
    SchemaItemReferenceMapper schemaItemReferenceMapper;

    @Override
    public SchemaItemReferencePojo findById(BaseMTMIdentity identity) {
        SchemaItemReference schemaItemReferenceEntity = schemaItemReferenceRepository.findById(identity)
                .orElseThrow(() -> {
                    log.error("Error: Resource SchemaItemReference with identity {} is not found", identity);
                    return new ResourceNotFoundException("Resource Schema Item Reference not found");
                });
        return schemaItemReferenceMapper.entityToDto(schemaItemReferenceEntity);
    }

}