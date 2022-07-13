package com.umldesigner.schema.foreign_key.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.umldesigner.infrastructure.domain.entities.BaseMEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "s_fk")
/**
 * @apiNote value1 and value2 in the identity refer to id's of SItems
 */
public class SFK extends BaseMEntity {
    private static final long serialVersionUID = 3L;

    @NonNull
    @Column(name = "onUpdate")
    private Boolean onUpdate;

    @NonNull
    @Column(name = "onDelete")
    private Boolean onDelete;

}