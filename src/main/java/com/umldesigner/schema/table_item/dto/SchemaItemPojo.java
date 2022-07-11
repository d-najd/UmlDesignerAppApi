package com.umldesigner.schema.table_item.dto;

import com.umldesigner.infrastructure.pojo.pojos.BasePojo;
import com.umldesigner.schema.table.dto.SchemaTablePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaItemPojo extends BasePojo {

    // the pojo has no need to know positions
    // private Integer position;

    private String type;

    private String value;

    private SchemaTablePojo table;

}