package com.nttdata.di4.netflixfilms.dto;

import com.nttdata.di4.netflixfilms.entities.RecommendedAge;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.Year;

public class FilmDto implements Serializable {
    private static final long serialVersionUID = 8506271549413787034L;

    private Long id;
    private String name;
    private Year year;
    private RecommendedAgeDto recommendedAge;
    private Boolean advertising;
    private String shortDescription;
    private String longDescription;


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Year getYear() { return year; }

    public void setYear(Year year) { this.year = year; }

    public RecommendedAgeDto getRecommendedAge() { return recommendedAge; }

    public void setRecommendedAge(RecommendedAgeDto recommendedAge) { this.recommendedAge = recommendedAge; }

    public Boolean getAdvertising() { return advertising; }

    public void setAdvertising(Boolean advertising) { this.advertising = advertising; }

    public String getShortDescription() { return shortDescription; }

    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getLongDescription() { return longDescription; }

    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }
}
