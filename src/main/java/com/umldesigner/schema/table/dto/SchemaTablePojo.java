package com.umldesigner.schema.table.dto;

import com.umldesigner.infrastructure.pojo.UmlObjectPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaTablePojo extends UmlObjectPojo {
    private String title;
}