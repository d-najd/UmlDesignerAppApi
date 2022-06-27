package com.umldesigner.schema.item.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.dto.SchemaItemPojo;

@Component
public class SchemaItemMapperImpl extends AbstractGeneralMapper implements SchemaItemMapper {

    @Autowired
    public SchemaItemMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SchemaItemPojo entityToDto(SchemaItem schemaItem) {
        return this.modelMapper.map(schemaItem, SchemaItemPojo.class);
    }

    @Override
    public SchemaItem dtoToEntity(SchemaItemPojo schemaItemPojo) {
        return this.modelMapper.map(schemaItemPojo, SchemaItem.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(SchemaItem entity, SchemaItemPojo dto) {
        entity.setPosition(dto.getPosition());
        entity.setType(dto.getType());
        entity.setValue(dto.getValue());
    }

}