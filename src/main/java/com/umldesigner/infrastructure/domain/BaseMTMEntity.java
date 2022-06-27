package com.umldesigner.infrastructure.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseMTMEntity implements Serializable { //NOTE this shouldn't extends BaseMTMIdentity.java
    private static final long serialVersionUID = 3L; //NOTE this and BaseMTMIdentity are different things so different serialUID's

}