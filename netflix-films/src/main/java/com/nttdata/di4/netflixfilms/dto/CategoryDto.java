package com.nttdata.di4.netflixfilms.dto;

import java.io.Serializable;

public class CategoryDto implements Serializable {
    private static final long serialVersionUID = -8094316929458363179L;

    private Long id;
    private String name;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
