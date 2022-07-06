package com.umldesigner.schema.template.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.template.domain.TItem;
import com.umldesigner.schema.template.dto.TPojo;

public interface TMapper extends GeneralMapper<TPojo, TItem> {
    public void mapRequestedFieldForUpdate(TItem entity, TPojo dto);
}