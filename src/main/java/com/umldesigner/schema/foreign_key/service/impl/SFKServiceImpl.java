package com.umldesigner.schema.foreign_key.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMIdentity;
import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.schema.foreign_key.dto.SFKPojo;
import com.umldesigner.schema.foreign_key.mapper.SFKMapper;
import com.umldesigner.schema.foreign_key.repository.SFKRepository;
import com.umldesigner.schema.foreign_key.service.SFKService;
import com.umldesigner.schema.table_item.service.SItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SFKServiceImpl implements SFKService {

    @Autowired
    SFKRepository sfkRepository;

    @Autowired
    SFKMapper sfkMapper;

    @Autowired
    SItemService sItemService;

    @Override
    public SFKPojo findById(String fUuid, String sUuid) {
        log.debug("Execute findById with parameters {}, {}", fUuid, sUuid);

        Integer firstSItemId = sItemService.findByUuid(fUuid).getId();
        Integer secondSItemId = sItemService.findByUuid(sUuid).getId();

        SFK sfkEntity = sfkRepository.findById(new BaseMIdentity(firstSItemId, secondSItemId))
                .orElseThrow(() -> {
                    log.error("Error: Resource Schema Primary Key with identity {} is not found",
                            new BaseMIdentity(firstSItemId, secondSItemId));
                    return new ResourceNotFoundException("Resource Schema Primary Key not found");
                });
        return sfkMapper.entityToDto(sfkEntity);
    }
}