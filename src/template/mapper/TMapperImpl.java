package com.umldesigner.schema.template.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.template.domain.TItem;
import com.umldesigner.schema.template.dto.TPojo;

@Component
public class TMapperImpl extends AbstractGeneralMapper implements TMapper {

    @Autowired
    public TMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public TPojo entityToDto(TItem tItem) {
        return this.modelMapper.map(tItem, TPojo.class);
    }

    @Override
    public TItem dtoToEntity(TPojo tPojo) {
        return this.modelMapper.map(tPojo, TItem.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(TItem entity, TPojo dto) {
        entity.setType(dto.getType());
    }
}