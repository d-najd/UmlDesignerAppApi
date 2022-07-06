package com.umldesigner.schema.table_items.service;

import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.table_items.dto.TableItemPojo;

@Service
public interface TableItemService {
    public TableItemPojo findById(BaseMTMIdentity id);
}