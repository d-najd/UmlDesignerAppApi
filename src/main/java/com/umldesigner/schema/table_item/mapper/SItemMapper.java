package com.umldesigner.schema.table_item.mapper;

import com.umldesigner.infrastructure.mapper.GeneralMapper;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;

public interface SItemMapper extends GeneralMapper<SItemPojo, SItem> {

  public void mapRequestedFieldForUpdate(SItem entity, SItemPojo dto);

}
