package com.umldesigner.schema.template.service;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.template.dto.TPojo;

@Service
public interface TService {
    public TPojo findById(Integer id);
}