package com.umldesigner.schema.table.utils.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table.dto.STablePojo;
import com.umldesigner.schema.table.utils.item.SItemTestUtil;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;

public class STableTestUtil {
    public static STable createMockTableEntity() {
        STable mock = new STable();
        List<SItem> items = new ArrayList<>();
        items.add(SItemTestUtil.createMockSItemEntity());
        items.add(SItemTestUtil.createMockSItemEntity());
        mock.setItems(items);
        mock.setId(0);
        mock.setUuid("Mock UUID");
        mock.setTitle("Mock Title");
        mock.setX(10.1f);
        mock.setY(-10.1f);
        return mock;
    }

    public static STablePojo createMockTablePojo(List<SItemPojo> mockItemPojoList, String title, float x, float y,
            String uuid) {
        STablePojo mock = new STablePojo();
        mock.setUuid(uuid);
        mock.setItems(mockItemPojoList);
        mock.setTitle(title);
        mock.setX(x);
        mock.setY(y);
        return mock;
    }

    public static STablePojo createMockTablePojo() {
        List<SItemPojo> items = new ArrayList<>(
                Arrays.asList(SItemTestUtil.createMockSItemPojo(), SItemTestUtil.createMockSItemPojo()));
        return createMockTablePojo(items, "Mock Title", 10.1f, -10.1f, "Mock Uuid");
    }

}
