package com.umldesigner.schema.item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.umldesigner.infrastructure.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_item")
public class SchemaItem extends BaseEntity {
    
    @NonNull
    @Column(name = "position")
    private Integer position;

    @NonNull
    @Column(name = "type")
    private String type;

    @NonNull
    @Column(name = "value")
    private String value;
}