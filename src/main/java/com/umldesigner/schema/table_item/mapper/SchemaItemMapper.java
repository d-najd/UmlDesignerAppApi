package com.umldesigner.schema.table_item.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.table_item.domain.SchemaItem;
import com.umldesigner.schema.table_item.dto.SchemaItemPojo;

public interface SchemaItemMapper extends GeneralMapper<SchemaItemPojo, SchemaItem> {
    public void mapRequestedFieldForUpdate(SchemaItem entity, SchemaItemPojo dto);
}