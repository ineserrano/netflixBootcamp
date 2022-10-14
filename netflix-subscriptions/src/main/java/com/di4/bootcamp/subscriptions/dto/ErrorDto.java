package com.di4.bootcamp.subscriptions.dto;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 3731083927080723562L;

    private String name;
    private String value;

    public ErrorDto(String name, String value) {
        super();
        this.name = name;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(value).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof ErrorDto)) {
            return false;
        }

        ErrorDto castOther = (ErrorDto) other;
        return new EqualsBuilder().append(name, castOther.name).append(value, castOther.value)
                                  .isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("value", value).toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
