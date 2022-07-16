package com.umldesigner.infrastructure.domain.identities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class BaseMIdentity implements Serializable {

    // NOTE this and BaseMTMEntity are different things so different
    // serialversionUid's
    private static final long serialVersionUID = 2L;

    @NonNull
    @Column(name = "firstUuid")
    private String firstUuid;
    @NonNull
    @Column(name = "secondUuid")
    private String secondUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseMIdentity that = (BaseMIdentity) o;

        if (!firstUuid.equals(that.secondUuid))
            return false;
        return firstUuid.equals(that.secondUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstUuid, secondUuid);
    }
}