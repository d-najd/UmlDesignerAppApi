package com.umldesigner.schema.table.mapper.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.dto.SchemaTablePojo;
import com.umldesigner.schema.table.mapper.SchemaTableMapper;
import com.umldesigner.schema.table_item.mapper.SchemaItemMapper;

@Component
public class SchemaTableMapperImpl extends AbstractGeneralMapper implements SchemaTableMapper {

    @Autowired
    SchemaItemMapper schemaItemMapper;

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
        //create AbstractUmlMapper and UmlMapper which extends the AbstractGeneralMapper and GeneralMapper but map the x and y coordinates automatically
        entity.setX(dto.getX());
        entity.setY(dto.getY());

        /*  not sure how to do mapping for fields so I will leave it for now
        entity.setItems(dto.getItems());

        SchemaItem[] schemaItems = (SchemaItem[]) entity.getItems().toArray();
        SchemaItemPojo[] schemaItemsPojo = (SchemaItemPojo[]) dto.getItems().toArray();

        //I am 99% sure there is a better way
        for(int i = 0; i < schemaItems.length; i++){
            for (int b = 0; b < schemaItemsPojo.length; b++){
                if (schemaItems[i].getUuid().equals(schemaItemsPojo[b].getUuid())){
                    schemaItemMapper.mapRequestedFieldForUpdate(schemaItems[i], schemaItemsPojo[i]);
                    break;
                }
            }
        }
        */

        
    }
}