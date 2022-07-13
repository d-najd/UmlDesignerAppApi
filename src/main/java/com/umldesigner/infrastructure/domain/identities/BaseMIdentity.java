package com.umldesigner.infrastructure.domain.identities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
public class BaseMIdentity implements Serializable {

    // NOTE this and BaseMTMEntity are different things so different
    // serialversionUid's
    private static final long serialVersionUID = 2L;

    @NonNull
    @Column(name = "firstId")
    private Integer firstId;
    @NonNull
    @Column(name = "secondId")
    private Integer secondId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseMIdentity that = (BaseMIdentity) o;

        if (!firstId.equals(that.secondId))
            return false;
        return firstId.equals(that.secondId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstId, secondId);
    }
}