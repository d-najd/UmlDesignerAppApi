package com.umldesigner.schema.template.dto;

import com.umldesigner.infrastructure.pojo.pojos.BasePojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TPojo extends BasePojo {        
    private String type;
}