package com.umldesigner.schema.table.dto;

import java.util.ArrayList;
import java.util.List;

import com.umldesigner.infrastructure.pojo.pojos.UmlObjectPojo;
import com.umldesigner.schema.table_item.dto.SItemPojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class STablePojo extends UmlObjectPojo {

    private String title;

    // Set<SchemaItemPojo> tableItems = new HashSet<>();

    private List<SItemPojo> items = new ArrayList<>();

}