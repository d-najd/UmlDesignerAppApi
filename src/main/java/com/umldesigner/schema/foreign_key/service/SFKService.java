package com.umldesigner.schema.foreign_key.service;

import org.springframework.stereotype.Service;

import com.umldesigner.schema.foreign_key.dto.SFKPojo;

@Service
public interface SFKService {
   public SFKPojo findById(String fUuid, String sUuid);
}
