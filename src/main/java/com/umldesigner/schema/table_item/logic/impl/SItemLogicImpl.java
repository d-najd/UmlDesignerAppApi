package com.umldesigner.schema.table_item.logic.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.logic.SItemLogic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SItemLogicImpl implements SItemLogic {

    @Override
    public Integer getNextPosition(List<SItem> sItemList) {
        log.debug("Execute setNextPosition with parameters {}", sItemList);
        if (sItemList == null || sItemList.size() == 0) {
            return 0;
        } else {
            Integer biggestPosition = sItemList.get(sItemList.size() - 1).getPosition();
            if (biggestPosition == null) { // if I leave it as null it will crash since apparantly *can't add x to null*
                return 0;
            }
            return biggestPosition + 1;
        }

    }
}
