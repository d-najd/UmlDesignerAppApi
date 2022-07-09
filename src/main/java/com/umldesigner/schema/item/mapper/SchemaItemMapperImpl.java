package com.umldesigner.schema.item.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.dto.SchemaItemPojo;

@Component
public class SchemaItemMapperImpl extends AbstractGeneralMapper implements SchemaItemMapper {

    public SchemaItemMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }
    
    /**
     * prevents a recursive bug where schemaItemPojo gets called over and over (from what I understand)
     * @apiNote this is probably a dumb way of doing things but oh well
     */
    @Override
    public void modelMapperConfig(){
        modelMapper.addMappings(new PropertyMap<SchemaItem, SchemaItemPojo>() {
            @Override
            protected void configure() {
                skip(destination.getTableId());
            }
        });
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