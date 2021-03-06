package com.umldesigner.schema.table.utils.item;

import java.util.ArrayList;
import java.util.List;

import com.umldesigner.schema.table.domain.STable;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.submodules.UmlDesignerShared.schema.table.dto.STablePojo;
import com.umldesigner.submodules.UmlDesignerShared.schema.table_item.dto.SItemPojo;

public class SItemTestUtil {

    public static SItem createMockSItemEntity() {
        SItem mock = new SItem();
        mock.setIsPrimaryKey(false);
        mock.setUuid("Mock UUID");
        mock.setPosition(0);
        mock.setType("Mock Type");
        mock.setValue("Mock Value");
        return mock;
    }

    public static SItem createMockSItemEntity(STable table, Boolean isPrimaryKey, String type, String value,
            String uuid, int position) {
        SItem mock = new SItem();
        mock.setIsPrimaryKey(isPrimaryKey);
        mock.setPosition(position);
        mock.setTable(table);
        mock.setType(type);
        mock.setUuid(uuid);
        mock.setValue(value);
        return mock;
    }

    /**
     * creates mock SItemPojo
     * 
     * @return the created mock object
     */
    public static SItemPojo createMockSItemPojo() {
        return createMockSItemPojo(null, false, "Mock Type", "Mock Value", "Mock Uuid");
    }

    /**
     * creates mock SItem with given values
     * 
     * @param table        the table that the mock points to
     * @param isPrimaryKey whether it is primary key or not
     * @param type         the type (ex String, int etc)
     * @param value        the value (ex "hello world", "John" etc)
     * @return the created mock object
     */
    public static SItemPojo createMockSItemPojo(STablePojo table, Boolean isPrimaryKey, String type, String value,
            String uuid) {
        SItemPojo mock = new SItemPojo();
        mock.setUuid(uuid);
        mock.setTable(table);
        mock.setIsPrimaryKey(isPrimaryKey);
        mock.setType(type);
        mock.setValue(value);
        return mock;
    }

    /**
     * creates a list of SItems with a given length
     * 
     * @param table        the table that the SItem points to
     * @param isPrimaryKey tells us whether the SItem is primary key or not, if null
     *                     will make each even number true and each odd false
     * @param type         the type of the sItem plus the position in the list (ex
     *                     "int 1")
     * @param value        the value of the type plus the position in the list (ex
     *                     "hello 1")
     * @param length       how many items we want to create, counts from 1
     * @return list of SItems, if something fails it will return empty list
     */
    public static List<SItemPojo> createMockSItemPojoList(
            STablePojo table,
            Boolean isPrimaryKey,
            String type,
            String value,
            int length) {

        List<SItemPojo> mockList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mockList.add(createMockSItemPojo(table, isPrimaryKey == null ? i % 2 == 0 : isPrimaryKey, type + " " + i,
                    value + " " + i, "Mock Uuid " + i));
        }
        return mockList;
    }

    public static List<SItem> createMockSItemList(
            STable table,
            Boolean isPrimaryKey,
            String type,
            String value,
            int length) {
        List<SItem> mockList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            mockList.add(createMockSItemEntity(table, isPrimaryKey == null ? i % 2 == 0 : isPrimaryKey, type + " " + i,
                    value + " " + i, "Mock Uuid " + i, i));
        }
        return mockList;
    }
}
