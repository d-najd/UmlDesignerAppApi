package com.umldesigner.infrastructure.domain.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseMTMEntity implements Serializable { //NOTE this shouldn't extends BaseMTMIdentity.java
    private static final long serialVersionUID = 3L; //NOTE this and BaseMTMIdentity are different things so different serialUID's

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO make sure this works
    private BaseMTMIdentity identity;
}