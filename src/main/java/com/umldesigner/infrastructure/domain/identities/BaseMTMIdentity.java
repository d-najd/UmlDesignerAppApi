package com.umldesigner.infrastructure.domain.identities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@MappedSuperclass
public class BaseMTMIdentity implements Serializable {
	private static final long serialVersionUID = 2L; //NOTE this and BaseMTMEntity are different things so different serialversionUid's
	
	@NonNull
	@Column(name = "value1")
    private Integer value1;
	@NonNull
	@Column(name = "value2")
    private Integer value2;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseMTMIdentity that = (BaseMTMIdentity) o;

        if (!value1.equals(that.value1)) 
        	return false;
        return value2.equals(that.value2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }
}