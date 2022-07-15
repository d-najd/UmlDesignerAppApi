package com.umldesigner.schema.table_item.logic;

import java.util.List;

import org.springframework.stereotype.Component;

import com.umldesigner.schema.table_item.domain.SItem;

@Component
public interface SItemLogic {

    /**
     * sets the position of the input pojo to a position 1 bigger than the biggest
     * position of the input list
     * ex: the input list has pojos with positions 1 and 3, so the biggest position
     * is 3 which means the input pojo's
     * position will be 3 + 1 = 4
     * 
     * @param schemaItemPojo    the input pojo, the one that gets its position set
     * @param oldSchemaItemList the input list, the list we use to find the biggest
     *                          position
     * @implNote {@link com.umldesigner.schema.table_item.domain.SItem#SchemaItem()}
     *           is used because the pojo shouldn't hold logic for the positions
     * @return the new position
     */
    public Integer getNextPosition(List<SItem> sItemList);
}
