package com.umldesigner.schema.table.dto;

import java.util.Set;

import com.umldesigner.infrastructure.pojo.pojos.UmlObjectPojo;
import com.umldesigner.schema.item.dto.SchemaItemPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SchemaTablePojo extends UmlObjectPojo {
    private String title;
    //Set<SchemaItemPojo> tableItems = new HashSet<>();
    private Set<SchemaItemPojo> items;

    
}