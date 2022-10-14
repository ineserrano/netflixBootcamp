package com.nttdata.di4.netflixfilms.dto;

import javax.persistence.Column;
import java.io.Serializable;

public class RecommendedAgeDto implements Serializable {
    private static final long serialVersionUID = 2942653317002341727L;

    private Long id;
    private String name;
    private int minAge;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
