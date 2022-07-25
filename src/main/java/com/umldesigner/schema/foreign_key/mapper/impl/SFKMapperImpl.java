package com.umldesigner.schema.foreign_key.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;
import com.umldesigner.schema.foreign_key.mapper.SFKMapper;

@Component
public class SFKMapperImpl extends AbstractGeneralMapper implements SFKMapper {

    @Autowired
    public SFKMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SFKPojo entityToDto(SFK sfk) {
        return this.modelMapper.map(sfk, SFKPojo.class);
    }

    @Override
    public SFK dtoToEntity(SFKPojo sfkPojo) {
        return this.modelMapper.map(sfkPojo, SFK.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(SFK entity, SFKPojo dto) {
        entity.setOnUpdate(dto.getOnUpdate());
        entity.setOnDelete(dto.getOnDelete());
    }
}