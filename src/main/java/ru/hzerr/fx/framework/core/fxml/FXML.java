package ru.hzerr.fx.framework.core.fxml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public record FXML(String location, Object controller) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FXML fxml = (FXML) o;

        return new EqualsBuilder()
                .append(location, fxml.location)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(location)
                .toHashCode();
    }
}
