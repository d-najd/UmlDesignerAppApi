package com.umldesigner.schema.template.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.umldesigner.infrastructure.domain.entities.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TItem")
public class TItem extends BaseEntity {
   	private static final long serialVersionUID = 1L;
    
    @NonNull
    @Column(name = "type")
    private String type;
}