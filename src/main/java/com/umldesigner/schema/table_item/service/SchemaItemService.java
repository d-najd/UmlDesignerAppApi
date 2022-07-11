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

    public SchemaItemPojo createSchemaItem(String tUuid, SchemaItemPojo schemaItemPojo);

    public Set<SchemaItemPojo> createSchemaItemSet(String tUuid, Set<SchemaItemPojo> schemaItemPojoSet);

    public List<SchemaItemPojo> createSchemaItemList(String tUuid, List<SchemaItemPojo> schemaItemPojoList);

    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo);

    public void removeSchemaItem(String uuid);

}