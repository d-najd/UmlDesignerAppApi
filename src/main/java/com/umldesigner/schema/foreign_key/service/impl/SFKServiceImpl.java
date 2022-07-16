package com.umldesigner.schema.foreign_key.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMIdentity;
import com.umldesigner.infrastructure.exception.ResourceNotFoundException;
import com.umldesigner.infrastructure.pojo.identities.BaseMIdentityPojo;
import com.umldesigner.schema.foreign_key.domain.SFK;
import com.umldesigner.schema.foreign_key.dto.SFKPojo;
import com.umldesigner.schema.foreign_key.fascade.SFKFascade;
import com.umldesigner.schema.foreign_key.mapper.SFKMapper;
import com.umldesigner.schema.foreign_key.repository.SFKRepository;
import com.umldesigner.schema.foreign_key.service.SFKService;

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
    SFKFascade sfkFascade;

    @Override
    public SFKPojo findById(String fUuid, String sUuid) {
        log.debug("Execute findById with parameters {}, {}", fUuid, sUuid);

        SFK sfkEntity = sfkRepository.findById(new BaseMIdentity(fUuid, sUuid))
                .orElseThrow(() -> {
                    log.error("Error: Resource Schema Primary Key with identity {} is not found",
                            new BaseMIdentity(fUuid, sUuid));
                    return new ResourceNotFoundException("Resource Schema Primary Key not found");
                });
        return sfkMapper.entityToDto(sfkEntity);
    }

    public List<SFKPojo> findAll() {
        log.debug("Execute getAll");

        return sfkMapper.mapList(sfkRepository.findAll(), SFKPojo.class);
    }

    // TODO whem implementing multiple projects make sure that the foreign keys
    // don't point across multiple projects and realities

    @Override
    public SFKPojo createForeignKey(String fUuid, String sUuid, SFKPojo pojo) {
        log.debug("Execute createForeignKey with parameters {}. {}. {}", fUuid, sUuid, pojo);

        pojo.setIdentity(new BaseMIdentityPojo(fUuid, sUuid));
        sfkFascade.isValid(fUuid, sUuid, pojo);

        SFK persistedSfk = sfkMapper.dtoToEntity(pojo);

        return sfkMapper.entityToDto(persistedSfk);
    }
}