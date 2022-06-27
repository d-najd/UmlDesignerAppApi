package com.umldesigner.schema.table.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.umldesigner.infrastructure.domain.UmlObjectEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_table")
public class SchemaTable extends UmlObjectEntity{
    private static final long serialVersionUID = 1L;
    
    @NonNull
    @Column(name = "title")
    private String title;
}