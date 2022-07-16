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
    SItemService sItemService; // TODO find a way to get rid of this

    @Override
    public boolean isValid(String fUuid, String sUuid, SFKPojo pojo) {
        log.debug("Execute isValid with parameters {}, {}, {}", fUuid, sUuid, pojo);

        if (sameTableFKCheck(fUuid, sUuid)) {
            log.error("Foreign key links to the same table with parameters {}. {}. {}", fUuid, sUuid, pojo);
            throw new IllegalArgumentException("Foreign key links to the same table");
        }

        if (!validArgumentsCheck(pojo)) {
            log.error("Invalid Arguemnt Entered for OnDelete or OnUpdate with parameters {}", pojo);
            throw new IllegalArgumentException(
                    "Invalid Argument entered for OnDelete or OnUpdate, available arguments are: No Action, REstrict, Cascade, Set Null, Set Default");
        }

        if (!fkIdentityMatch(fUuid, sUuid, pojo)) {
            log.error(
                    "Identity located in the {} and identity created using {}, {} don't match, create a identity using fUuid and sUuid and set it",
                    pojo, fUuid, sUuid);
            throw new InternalError("Misconfiguration on the server side");
        }

        return true;
    }

    @Override
    public boolean sameTableFKCheck(String fUuid, String sUuid) {
        log.debug("Execute sameTableFKCheck with parameters {}, {}", fUuid, sUuid);
        SItem firstItem = sItemService.findByUuid(fUuid);
        SItem secondItem = sItemService.findByUuid(sUuid);

        return firstItem.getTable().equals(secondItem.getTable());
    }

    @Override
    public boolean validArgumentsCheck(SFKPojo pojo) {
        log.debug("Execute validArguemntsCheck with parameters {}", pojo);
        String[] arguments = { pojo.getOnDelete(), pojo.getOnUpdate() };

        for (String argument : arguments) {
            switch (argument) {
                case "na": // no action
                case "re": // restrict
                case "ca": // cascade
                case "sn": // set null
                case "sd": // set default
                    break;
                default: // invalid argument
                    return false;
            }
        }

        return true;
    }

    @Override
    public boolean fkIdentityMatch(String fUuid, String sUuid, SFKPojo pojo) {
        log.debug("Execute fkIdentityMatch with parameters {}, {}. {}", fUuid, sUuid, pojo);

        return pojo.getIdentity().equals(new BaseMIdentityPojo(fUuid, sUuid));
    }
}
