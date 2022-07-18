package com.umldesigner.infrastructure.domain.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

import com.umldesigner.infrastructure.domain.identities.BaseMIdentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
// NOTE this shouldn't extends BaseMTMIdentity.java
public class BaseMEntity implements Serializable {
    // NOTE this and BaseMTMIdentity are different things so different serialUID's
    private static final long serialVersionUID = 3L;

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BaseMIdentity identity;

}