package com.umldesigner.schema.foreign_key.fascade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.umldesigner.infrastructure.pojo.identities.BaseMIdentityPojo;
import com.umldesigner.schema.foreign_key.dto.SFKPojo;
import com.umldesigner.schema.foreign_key.fascade.SFKFascade;
import com.umldesigner.schema.table_item.domain.SItem;
import com.umldesigner.schema.table_item.service.SItemService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SFKFascadeImpl implements SFKFascade {

    @Autowired
    SItemService sItemService;

    @Override
    public boolean isValid(String fUuid, String sUuid, SFKPojo pojo) {
        // TODO Auto-generated method stub
        return false;
    }

    // TODO test this
    @Override
    public boolean sameTableFKCheck(String fUuid, String sUuid) {
        SItem firstItem = sItemService.findByUuid(fUuid);
        SItem secondItem = sItemService.findByUuid(sUuid);

        // prettier-ignore

        return firstItem.getTable().equals(secondItem.getTable());
        /*
         * why prettier
         * 
         * if (firstItem.getTable().equals(secondItem.getTable())) {
         * log.debug(
         * "Error: client tried to create Schema Foreign Key between items from the same table with parameters {}, {}"
         * ,
         * fUuid, sUuid);
         * throw new
         * IllegalStateException("Can't create Foreign key between items from the same table"
         * );
         * }
         * 
         */
    }

    @Override
    public boolean validArgumentsCheck(SFKPojo pojo) {
        
        return false;
    }

    // TODO test this
    @Override
    public boolean fkIdentityMatch(String fUuid, String sUuid, SFKPojo pojo) {
        SItem fItem = sItemService.findByUuid(fUuid);
        SItem sItem = sItemService.findByUuid(sUuid);

        return pojo.getIdentity().equals(new BaseMIdentityPojo(fItem.getId(), sItem.getId()));
    }
}
