package com.umldesigner.schema.table_item.mapper.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.mapper.SItemMapper;
import com.umldesigner.submodules.UmlDesignerShared.schema.table_item.dto.SItemPojo;

@Component
public class SItemMapperImpl extends AbstractGeneralMapper implements SItemMapper {

  public SItemMapperImpl(ModelMapper modelMapper) {
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
        new PropertyMap<SItem, SItemPojo>() {

          @Override
          protected void configure() {
            skip(destination.getTable());
          }
        });
  }

  @Override
  public SItemPojo entityToDto(SItem schemaItem) {
    return this.modelMapper.map(schemaItem, SItemPojo.class);
  }

  @Override
  public SItem dtoToEntity(SItemPojo schemaItemPojo) {
    return this.modelMapper.map(schemaItemPojo, SItem.class);
  }

  @Override
  public void mapRequestedFieldForUpdate(
      SItem entity,
      SItemPojo dto) { // very clever
    // entity.setPosition(dto.getPosition()); we want the server to decide this not
    // the user
    entity.setType(dto.getType());
    entity.setValue(dto.getValue());
    entity.setIsPrimaryKey(dto.getIsPrimaryKey());
  }

   
}
