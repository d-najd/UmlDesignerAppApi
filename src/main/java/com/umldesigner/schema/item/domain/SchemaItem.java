package com.umldesigner.schema.item.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.infrastructure.domain.entities.BaseEntity;
import com.umldesigner.schema.table.domain.SchemaTable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_item")
public class SchemaItem extends BaseEntity {
   	private static final long serialVersionUID = 1L;

    @JsonIgnore
    @ManyToMany(mappedBy = "tableItems")
    private Set<SchemaTable> boards = new HashSet<>();
    
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