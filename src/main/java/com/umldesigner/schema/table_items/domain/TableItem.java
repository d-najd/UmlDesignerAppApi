package com.umldesigner.schema.table_items.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.umldesigner.infrastructure.domain.entities.BaseMTMEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_table_items")
public class TableItem extends BaseMTMEntity {
   	private static final long serialVersionUID = 3L;
}