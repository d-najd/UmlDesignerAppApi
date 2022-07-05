package com.umldesigner.schema.table.dto;

import com.umldesigner.infrastructure.pojo.pojos.UmlObjectPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaTablePojo extends UmlObjectPojo {
    private String title;
}