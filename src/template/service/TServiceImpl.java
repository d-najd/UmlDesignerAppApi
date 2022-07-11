package com.umldesigner.schema.template.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.umldesigner.schema.template.domain.TItem;
import com.umldesigner.schema.template.dto.TPojo;
import com.umldesigner.schema.template.mapper.TMapper;
import com.umldesigner.schema.template.repository.TRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class TServiceImpl implements TService {

    @Autowired
    TRepository tRepository;

    @Autowired
    TMapper tMapper;

    @Override
    public TPojo findById(Integer id) {
        try {
            TItem tItem = tRepository.findById(id).orElseThrow(NotFoundException::new);
            return tMapper.entityToDto(tItem);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}