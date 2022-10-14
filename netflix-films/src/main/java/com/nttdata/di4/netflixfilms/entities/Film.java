package com.nttdata.di4.netflixfilms.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FILM")
public class Film implements Serializable {
    private static final long serialVersionUID = -7435110699941838266L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "YEAR", nullable = false)
    private Year year;
    @Column(name = "SHORT_DESC", nullable = false)
    private String shortDescription;
    @Column(name = "LONG_DESC")
    private String longDescription;
    @ManyToOne(fetch = FetchType.LAZY)
    private RecommendedAge recommendedAge;
    @Column(name = "advertising")
    private String advertising;
    @ElementCollection
    @Column(name = "category_id")
    @CollectionTable(name = "film_categories", joinColumns = @JoinColumn(name = "film_id"))
    private List<Long> categories = new ArrayList<>();


    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Year getYear() { return year; }

    public void setYear(Year year) { this.year = year; }

    public String getShortDescription() { return shortDescription; }

    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getLongDescription() { return longDescription; }

    public void setLongDescription(String longDescription) { this.longDescription = longDescription; }

    public RecommendedAge getRecommendedAge() { return recommendedAge; }

    public void setRecommendedAge(RecommendedAge recommendedAge) { this.recommendedAge = recommendedAge; }

    public String getAdvertising() { return advertising; }

    public void setAdvertising(String advertising) { this.advertising = advertising; }

    public List<Long> getCategories() { return categories; }

    public void setCategories(List<Long> categories) { this.categories = categories; }
}
