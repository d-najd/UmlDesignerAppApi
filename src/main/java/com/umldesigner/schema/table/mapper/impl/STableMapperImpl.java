package com.umldesigner.schema.table.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.mapper.STableMapper;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;
import com.umldesigner.schema.table_item.mapper.SItemMapper;

@Component
public class STableMapperImpl extends AbstractGeneralMapper implements STableMapper {

    @Autowired
    SItemMapper sItemMapper;

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
        List<SItemPojo> e = dto.getItems();
        List<SItem> te = new ArrayList<>();
        for (SItemPojo pojo : e) {
            te.add(sItemMapper.dtoToEntity(pojo));
        }
        entity.setItems(te);
        */
    }
}