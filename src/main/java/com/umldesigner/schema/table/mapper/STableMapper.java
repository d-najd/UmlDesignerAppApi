package com.umldesigner.schema.table.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;

public interface STableMapper extends GeneralMapper<STablePojo, STable> {
    public void mapRequestedFieldForUpdate(STable entity, STablePojo dto);
}