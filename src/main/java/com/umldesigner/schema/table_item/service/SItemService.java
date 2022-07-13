package com.umldesigner.schema.table_item.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.dto.SItemPojo;

@Service
public interface SItemService {

    public SItemPojo findById(Integer id);

    public SItemPojo getByUuid(String uuid);

    public SItem findByUuid(String uuid);

    public List<SItemPojo> getAll();

    public List<SItemPojo> getAllByTableUuid(String tUuid);

    public SItemPojo createSchemaItem(String tUuid, SItemPojo sItemPojo);

    public SItemPojo createSchemaItem(String tUuid, SItemPojo sItemPojo, Integer position)
            throws NotFoundException;

    /**
     * creates list of schemaItems to a given table
     * 
     * @param tUuid              uuid of the table where the items will be located
     * @param sItemPojos pojo list of the list of items we want to create
     * @return the created list
     */
    public List<SItemPojo> createSchemaItemList(String tUuid, List<SItemPojo> sItemPojos);

    public Set<SItemPojo> createSchemaItemSet(String tUuid, Set<SItemPojo> sItemPojos);

    public SItemPojo updateSchemaItem(String uuid, SItemPojo sItemPojo);

    /**
     * Swaps the positions of 2 Schema Items
     * 
     * @param tUuid uuid of the table where the 2 schema items are located at
     * @param fUuid uuid of the first item being swapped
     * @param sUuid uuid of the second item being swapped
     * @apiNote this won't work on items that aren't in the same table if it wasn't
     *          obvious
     */
    public void swapSchemaItems(String tUuid, String fUuid, String sUuid);

    public void removeSchemaItem(String uuid);

}