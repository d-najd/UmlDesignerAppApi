package com.umldesigner.infrastructure.pojo.identities;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMIdentityPojo {

    private Integer firstId;
    private Integer secondId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseMIdentityPojo that = (BaseMIdentityPojo) o;

        if (!firstId.equals(that.firstId))
            return false;
        return secondId.equals(that.secondId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstId, secondId);
    }
}