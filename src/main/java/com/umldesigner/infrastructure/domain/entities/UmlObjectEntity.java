package com.umldesigner.infrastructure.domain.entities;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class UmlObjectEntity extends BaseEntity {

    @Column(name = "x")
    @NonNull
    private Float x;

    @Column(name = "y")
    @NonNull
    private Float y;

}