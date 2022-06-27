package com.umldesigner.schema.item.service;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.item.dto.SchemaItemPojo;

@Service
public interface SchemaItemService {
    public SchemaItemPojo findById(Integer id);
}