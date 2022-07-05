package com.umldesigner.schema.item_reference.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.item_reference.domain.SchemaItemReference;
import com.umldesigner.schema.item_reference.dto.SchemaItemReferencePojo;
import com.umldesigner.schema.item_reference.mapper.SchemaItemReferenceMapper;
import com.umldesigner.schema.item_reference.repository.SchemaItemReferenceRepository;

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
        try {
            SchemaItemReference schemaItemReferenceEntity = schemaItemReferenceRepository.findById(identity).orElseThrow(NotFoundException::new);
            return schemaItemReferenceMapper.entityToDto(schemaItemReferenceEntity); 
        } catch (NotFoundException e){
            e.printStackTrace();
        } 
        return null;
    }

}