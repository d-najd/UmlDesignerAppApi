package com.umldesigner.schema.table_items.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;
import com.umldesigner.schema.table_items.domain.TableItem;
import com.umldesigner.schema.table_items.dto.TableItemPojo;
import com.umldesigner.schema.table_items.mapper.TableItemMapper;
import com.umldesigner.schema.table_items.repository.TableItemRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class TableItemServiceImpl implements TableItemService {
    @Autowired
    TableItemRepository tableItemRepository;

    @Autowired
    TableItemMapper tableItemMapper;

    @Override
    public TableItemPojo findById(BaseMTMIdentity id) {
        try {
            TableItem tableItem = tableItemRepository.findById(id).orElseThrow(NotFoundException::new);
            return tableItemMapper.entityToDto(tableItem);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}