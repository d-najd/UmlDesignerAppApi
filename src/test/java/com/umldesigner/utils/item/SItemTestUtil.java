package com.umldesigner.utils.item;

import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;

public class SItemTestUtil {
    public static SItem createMockItemEntity() {
        SItem mock = new SItem();
        mock.setIsPrimaryKey(false);
        mock.setUuid("Mock UUID");
        mock.setPosition(0);
        mock.setType("Mock Type");
        mock.setValue("Mock Value");
        return mock;
    }

    public static SItemPojo createMockBookPojo(STablePojo table, Boolean isPrimaryKey, String type, String value) {
        SItemPojo mock = new SItemPojo();
        mock.setTable(table);
        mock.setIsPrimaryKey(isPrimaryKey);
        mock.setType(type);
        mock.setValue(value);
        return mock;
    }
}
