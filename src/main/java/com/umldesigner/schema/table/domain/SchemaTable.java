package com.umldesigner.schema.table.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umldesigner.infrastructure.domain.entities.UmlObjectEntity;
import com.umldesigner.schema.item.domain.SchemaItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schema_table")
public class SchemaTable extends UmlObjectEntity{
    private static final long serialVersionUID = 1L;
   
    /* 
    @ManyToMany
    @JoinTable(
            name = "schema_table_items",
            joinColumns = @JoinColumn(name = "value1"),
            inverseJoinColumns = @JoinColumn(name = "value2")
    )
    Set<SchemaItem> tableItems = new HashSet<>();
    */

    @JsonIgnore
    @OneToMany(mappedBy = "tableId")
    private Set<SchemaItem> items;
    
    @NonNull
    @Column(name = "title")
    private String title;
}