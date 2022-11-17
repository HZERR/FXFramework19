package ru.hzerr.fx.framework.core.fxml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class FXMLType {

    protected FXMLType() {
    }

    /**
     * Example:
     * /fxml/tab
     */
    public abstract String getPackage();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FXMLType fxmlType = (FXMLType) o;

        return new EqualsBuilder().append(getPackage(), fxmlType.getPackage()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getPackage()).toHashCode();
    }
}
