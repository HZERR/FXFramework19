package ru.hzerr.fx.framework.core.fxml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.checkerframework.checker.nullness.qual.NonNull;

public record FXML(String location, Object controller) {

    public FXML(@NonNull String location, @NonNull Object controller) {
        this.location = location;
        this.controller = controller;
    }

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
