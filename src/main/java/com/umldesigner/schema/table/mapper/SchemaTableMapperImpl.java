package com.umldesigner.schema.table.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.dto.SchemaTablePojo;

@Component
public class SchemaTableMapperImpl extends AbstractGeneralMapper implements SchemaTableMapper {

    @Autowired
    public SchemaTableMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SchemaTablePojo entityToDto(SchemaTable schemaTable) {
        return this.modelMapper.map(schemaTable, SchemaTablePojo.class);
    }

    @Override
    public SchemaTable dtoToEntity(SchemaTablePojo schemaTablePojo) {
        return this.modelMapper.map(schemaTablePojo, SchemaTable.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(SchemaTable entity, SchemaTablePojo dto) {
        entity.setTitle(dto.getTitle());
        entity.setX(dto.getX());
        entity.setY(dto.getY());
    }

}