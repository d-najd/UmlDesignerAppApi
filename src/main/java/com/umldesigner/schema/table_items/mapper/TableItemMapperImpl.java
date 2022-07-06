package com.umldesigner.schema.table_items.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.mapper.AbstractGeneralMapper;
import com.umldesigner.schema.table_items.domain.TableItem;
import com.umldesigner.schema.table_items.dto.TableItemPojo;

@Component
public class TableItemMapperImpl extends AbstractGeneralMapper implements TableItemMapper {

    @Autowired
    public TableItemMapperImpl(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public TableItemPojo entityToDto(TableItem tItem) {
        return this.modelMapper.map(tItem, TableItemPojo.class);
    }

    @Override
    public TableItem dtoToEntity(TableItemPojo tPojo) {
        return this.modelMapper.map(tPojo, TableItem.class);
    }

}