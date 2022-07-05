package com.umldesigner.schema.item.dto;

import com.umldesigner.infrastructure.pojo.pojos.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaItemPojo extends BasePojo {        
    private Integer position;
    private String type;
    private String value;
}