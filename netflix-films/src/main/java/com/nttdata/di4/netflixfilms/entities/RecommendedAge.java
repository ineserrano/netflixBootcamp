package com.nttdata.di4.netflixfilms.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "RECOMMENDED_AGE")
public class RecommendedAge implements Serializable {
    private static final long serialVersionUID = 3454111888480943163L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "MIN_AGE")
    private byte minAge;
    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recommendedAge")
    private List<Film> film;


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

    public byte getMinAge() { return minAge; }

    public void setMinAge(byte minAge) {
        this.minAge = minAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Film> getFilm() { return film; }

    public void setFilm(List<Film> film) { this.film = film; }
}
