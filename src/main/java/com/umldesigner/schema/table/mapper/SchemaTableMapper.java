package com.umldesigner.schema.table.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.table.domain.SchemaTable;
import com.umldesigner.schema.table.dto.SchemaTablePojo;

public interface SchemaTableMapper extends GeneralMapper<SchemaTablePojo, SchemaTable> {
    public void mapRequestedFieldForUpdate(SchemaTable entity, SchemaTablePojo dto);
}