package com.umldesigner.schema.item_reference.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.item_reference.domain.SchemaItemReference;
import com.umldesigner.schema.item_reference.dto.SchemaItemReferencePojo;

@Component
public class SchemaItemReferenceMapperImpl extends AbstractGeneralMapper implements SchemaItemReferenceMapper {

    @Autowired
    public SchemaItemReferenceMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SchemaItemReferencePojo entityToDto(SchemaItemReference schemaItemReference) {
        return this.modelMapper.map(schemaItemReference, SchemaItemReferencePojo.class);
    }

    @Override
    public SchemaItemReference dtoToEntity(SchemaItemReferencePojo schemaItemReferencePojo) {
        return this.modelMapper.map(schemaItemReferencePojo, SchemaItemReference.class);
    }
}