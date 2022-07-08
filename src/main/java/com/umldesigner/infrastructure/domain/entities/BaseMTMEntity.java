package com.umldesigner.infrastructure.domain.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.umldesigner.infrastructure.domain.identities.BaseMTMIdentity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseMTMEntity implements Serializable { //NOTE this shouldn't extends BaseMTMIdentity.java
    private static final long serialVersionUID = 3L; //NOTE this and BaseMTMIdentity are different things so different serialUID's

    @EmbeddedId
    private BaseMTMIdentity identity;

    @Column(name = "uuid", updatable = false)
	private UUID uuid;

    @PrePersist
	public void init() {
		uuid = UUID.randomUUID();
	}
}