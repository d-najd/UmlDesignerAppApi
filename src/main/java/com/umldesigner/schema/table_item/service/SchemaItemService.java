package com.umldesigner.schema.table_item.service;

import java.util.List;
import java.util.Set;

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

    public SchemaItemPojo createSchemaItem(SchemaItemPojo schemaItemPojo, String tUuid);

    public Set<SchemaItemPojo> createSchemaItemSet(String tUuid, Set<SchemaItemPojo> schemaItemPojoList);

    public Set<SchemaItem> createSchemaItemSetRaw(String tUuid, Set<SchemaItem> schemaItemList);

    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo);

    public void removeSchemaItem(String uuid);
}