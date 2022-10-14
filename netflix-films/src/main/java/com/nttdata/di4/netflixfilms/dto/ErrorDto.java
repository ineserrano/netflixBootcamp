package com.nttdata.di4.netflixfilms.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String value;

    public ErrorDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ErrorDto)) return false;
        ErrorDto castOther = (ErrorDto) other;
        return new EqualsBuilder().reflectionAppend(this, castOther).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("value", value).toString();
    }
}
