package com.umldesigner.schema.table.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import com.umldesigner.infrastructure.domain.entities.UmlObjectEntity;
import com.umldesigner.schema.table_item.domain.SItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_table")
public class STable extends UmlObjectEntity {

    private static final long serialVersionUID = 1L;

    /*
     * <pre>
     * 
     * @ManyToMany
     * 
     * @JoinTable(
     * name = "schema_table_items",
     * joinColumns = @JoinColumn(name = "value1"),
     * inverseJoinColumns = @JoinColumn(name = "value2")
     * )
     * Set<SchemaItem> tableItems = new HashSet<>();
     */

    @OneToMany(mappedBy = "table")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OrderBy("position ASC")
    private List<SItem> items;

    @NonNull
    @Column(name = "title")
    private String title;
}