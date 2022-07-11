package com.umldesigner.schema.table_item.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.umldesigner.schema.table_item.domain.SchemaItem;
import com.umldesigner.schema.table_item.dto.SchemaItemPojo;

@Service
public interface SchemaItemService {

    public SchemaItemPojo findById(Integer id);

    public SchemaItemPojo getByUuid(String uuid);

    public SchemaItem findByUuid(String uuid);

    public List<SchemaItemPojo> getAll();

    public List<SchemaItemPojo> getAllByTableUuid(String tUuid);

    public SchemaItemPojo createSchemaItem(String tUuid, SchemaItemPojo schemaItemPojo);

    public SchemaItemPojo createSchemaItem(String tUuid, SchemaItemPojo schemaItemPojo, Integer position)
            throws NotFoundException;

    public Set<SchemaItemPojo> createSchemaItemSet(String tUuid, Set<SchemaItemPojo> schemaItemPojoSet);

    /**
     * creates list of schemaItems to a given table
     * 
     * @param tUuid
     * @param schemaItemPojoList
     * @return
     */
    public List<SchemaItemPojo> createSchemaItemList(String tUuid, List<SchemaItemPojo> schemaItemPojoList);

    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo);

    /**
     * swaps the positions of 2 schema items
     * 
     * @param tUuid      Uuid of the table that the 2 items belong to
     * @param firstUuid  id of the first item
     * @param secondUuid id of the second item
     */
    public void swapSchemaItems(String tUuid, String firstUuid, String secondUuid);

    public void removeSchemaItem(String uuid);

}