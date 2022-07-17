package com.umldesigner.utils.table;

import java.util.ArrayList;

import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;

public class STableTestUtil {
    public static STable createMockTableEntity() {
        STable mock = new STable();
        ArrayList<SItem> items = new ArrayList<>();
        items.add(new SItem());
        items.add(new SItem());
        mock.setItems(items);
        mock.setUuid("Mock UUID");
        mock.setTitle("Mock Title");
        mock.setX(10.0f);
        mock.setY(-10.0f);
        return mock;
    }

    public static STablePojo createMockTablePojoEntity(ArrayList<SItemPojo> items, String title, float x, float y) {
        STablePojo mock = new STablePojo();
        mock.setItems(items);
        mock.setTitle(title);
        mock.setX(x);
        mock.setY(y);
        return mock;
    }


}
