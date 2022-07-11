package com.umldesigner.infrastructure.pojo.identities;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseMTMIdentityPojo {

    private Integer value1;
    private Integer value2;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseMTMIdentityPojo that = (BaseMTMIdentityPojo) o;

        if (!value1.equals(that.value1))
            return false;
        return value2.equals(that.value2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }
}