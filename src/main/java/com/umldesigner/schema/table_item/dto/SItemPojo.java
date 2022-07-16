package com.umldesigner.schema.table_item.dto;

import com.umldesigner.infrastructure.pojo.pojos.BasePojo;
import com.umldesigner.schema.table.dto.STablePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SItemPojo extends BasePojo {

    // the pojo has no need to know positions
    // private Integer position;

    private String type;

    private String tableUuid_;

    private String value;

    private STablePojo table;

    private Boolean isPrimaryKey = false;

}