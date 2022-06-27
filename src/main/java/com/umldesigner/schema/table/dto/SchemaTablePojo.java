package com.umldesigner.schema.table.dto;

import com.umldesigner.infrastructure.pojo.UmlObjectPojo;

import lombok.Data;
import lombok.Getter;

@Data
public class SchemaTablePojo extends UmlObjectPojo {
    private String title;
}