package com.umldesigner.schema.table.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.mapper.STableMapper;

@Component
public class STableMapperImpl extends AbstractGeneralMapper implements STableMapper {

    public STableMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public STablePojo entityToDto(STable schemaTable) {
        return this.modelMapper.map(schemaTable, STablePojo.class);
    }

    @Override
    public STable dtoToEntity(STablePojo schemaTablePojo) {
        return this.modelMapper.map(schemaTablePojo, STable.class);
    }

    @Override
    public void mapRequestedFieldForUpdate(STable entity, STablePojo dto) {
        entity.setTitle(dto.getTitle());
        // create AbstractUmlMapper and UmlMapper which extends the
        // AbstractGeneralMapper and GeneralMapper but map the x and y coordinates
        // automatically
        entity.setX(dto.getX());
        entity.setY(dto.getY());

        /*
         * not sure how to do mapping for fields so I will leave it for now
         * entity.setItems(dto.getItems());
         * 
         * SchemaItem[] schemaItems = (SchemaItem[]) entity.getItems().toArray();
         * SchemaItemPojo[] schemaItemsPojo = (SchemaItemPojo[])
         * dto.getItems().toArray();
         * 
         * //I am 99% sure there is a better way
         * for(int i = 0; i < schemaItems.length; i++){
         * for (int b = 0; b < schemaItemsPojo.length; b++){
         * if (schemaItems[i].getUuid().equals(schemaItemsPojo[b].getUuid())){
         * sItemMapper.mapRequestedFieldForUpdate(schemaItems[i],
         * schemaItemsPojo[i]);
         * break;
         * }
         * }
         * }
         */

    }
}