package com.umldesigner.schema.foreign_key.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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
    @Column(name = "onUpdate", updatable = false, length = 2)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String onUpdate = "ca";

    @NonNull
    @Column(name = "onDelete", updatable = false, length = 2)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private String onDelete = "ca";

}