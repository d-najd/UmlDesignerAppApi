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
import com.umldesigner.schema.table.domain.STable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_item")
public class SItem extends BaseEntity {

  private static final long serialVersionUID = 1L;

  // @JsonIgnore
  // @ManyToMany(mappedBy = "tableItems")
  // private Set<SchemaTable> boards = new HashSet<>();

  @JsonIgnore
  @ManyToOne(targetEntity = STable.class, fetch = FetchType.LAZY, optional = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @NonNull
  @JoinColumn(name = "tableUuid", referencedColumnName = "uuid", updatable = false)
  private STable table;

  @Column(name = "tableUuid", updatable = false, insertable = false)
  private String tableUuid_;

  @JsonIgnore
  @NonNull
  @Column(name = "position", insertable = false)
  private Integer position;

  @NonNull
  @Column(name = "type")
  private String type;

  @NonNull
  @Column(name = "value")
  private String value;

  @Column(name = "isPrimaryKey")
  @NonNull
  private Boolean isPrimaryKey = false;

}
