package com.umldesigner.schema.item.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.dto.SchemaItemPojo;

@Service
public interface SchemaItemService {
    public SchemaItemPojo findById(Integer id);

    public SchemaItemPojo getByUuid(String uuid);

    public SchemaItem findByUuid(String uuid);

    public List<SchemaItemPojo> getAll();

    public Set<SchemaItemPojo> createSchemaItemSet(Set<SchemaItemPojo> schemaItemPojoSet);

    public SchemaItemPojo createSchemaItem(SchemaItemPojo schemaItemPojo);

    public SchemaItemPojo updateSchemaItem(String uuid, SchemaItemPojo schemaItemPojo);

    public void removeSchemaItem(String uuid);
}