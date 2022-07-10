package com.umldesigner.schema.table_item.logic.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.umldesigner.schema.table_item.domain.SchemaItem;
import com.umldesigner.schema.table_item.logic.SchemaItemLogic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchemaItemLogicImpl implements SchemaItemLogic {
    //TODO this doesn't work properly while creating a table with multiple items

    @Override
    public Integer getNextPosition(List<SchemaItem> schemaItemList) {
        log.debug("Execute setNextPosition with parameters {}", schemaItemList);
        if (schemaItemList == null || schemaItemList.size() == 0){
            return 0;
        } else {
            Integer biggestPosition = schemaItemList.get(schemaItemList.size() - 1).getPosition(); 
            return biggestPosition + 1;
        }
        
    }   
}
