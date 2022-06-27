package com.umldesigner.infrastructure.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class UmlObjectEntity extends BaseEntity {
    @Column(name = "x")
    private float x;
    @Column(name = "y")
    private float y;
}