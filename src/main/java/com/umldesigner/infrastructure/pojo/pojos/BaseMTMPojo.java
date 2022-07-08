package com.umldesigner.infrastructure.pojo.pojos;

import com.umldesigner.infrastructure.pojo.identities.BaseMTMIdentityPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMTMPojo {
    private BaseMTMIdentityPojo identity;  
    
    private String uuid;
}
