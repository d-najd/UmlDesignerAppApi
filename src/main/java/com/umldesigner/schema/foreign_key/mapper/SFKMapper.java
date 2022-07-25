package com.umldesigner.schema.foreign_key.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.submodules.UmlDesignerShared.schema.foreign_key.dto.SFKPojo;

public interface SFKMapper extends GeneralMapper<SFKPojo, SFK> {
    public void mapRequestedFieldForUpdate(SFK entity, SFKPojo dto);
}
