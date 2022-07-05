package com.umldesigner.schema.item_reference.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.umldesigner.infrastructure.domain.entities.BaseMTMEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_reference") 
public class SchemaItemReference extends BaseMTMEntity {
    private static final long serialVersionUID = 3L;
}