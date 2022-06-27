package com.umldesigner.schema.item.dto;

import com.umldesigner.infrastructure.pojo.BasePojo;
import lombok.Data;

@Data
public class SchemaItemPojo extends BasePojo {
    private Integer position;
    private String type;
    private String value;
}