package com.umldesigner.schema.table_item.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    //@JsonIgnore
    //@ManyToMany(mappedBy = "tableItems")
    //private Set<SchemaTable> boards = new HashSet<>();
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "table_id", referencedColumnName = "id", nullable = false)
    private SchemaTable table;

    @JsonIgnore
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