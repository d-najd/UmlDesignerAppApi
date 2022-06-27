package com.umldesigner.schema.item.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.umldesigner.schema.item.domain.SchemaItem;
import com.umldesigner.schema.item.dto.SchemaItemPojo;
import com.umldesigner.schema.item.mapper.SchemaItemMapper;
import com.umldesigner.schema.item.repository.SchemaItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class SchemaItemServiceImpl implements SchemaItemService {
    @Autowired
    SchemaItemRepository schemaItemRepository;

    @Autowired
    SchemaItemMapper schemaItemMapper;

    @Override
    public SchemaItemPojo findById(Integer id) {
        try {
            SchemaItem schemaItemEntity = schemaItemRepository.findById(id).orElseThrow(NotFoundException::new);
            return schemaItemMapper.entityToDto(schemaItemEntity);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}