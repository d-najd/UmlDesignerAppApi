package com.umldesigner.schema.table_item.mapper.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table_item.domain.SchemaItem;
import com.umldesigner.schema.table_item.dto.SchemaItemPojo;
import com.umldesigner.schema.table_item.mapper.SchemaItemMapper;

@Component
public class SchemaItemMapperImpl extends AbstractGeneralMapper implements SchemaItemMapper {

  public SchemaItemMapperImpl(ModelMapper modelMapper) {
    super(modelMapper);
  }

  /**
   * prevents a recursive bug where schemaItemPojo gets called over and over (from
   * what I understand)
   * 
   * @apiNote this is probably a dumb way of doing things but oh well
   */
  @Override
  public void modelMapperConfig() {
    modelMapper.addMappings(
        new PropertyMap<SchemaItem, SchemaItemPojo>() {

          @Override
          protected void configure() {
            skip(destination.getTable());
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
  public void mapRequestedFieldForUpdate(
      SchemaItem entity,
      SchemaItemPojo dto) { // very clever
    // entity.setPosition(dto.getPosition()); we want the server to decide this not
    // the user
    entity.setType(dto.getType());
    entity.setValue(dto.getValue());
  }

}
