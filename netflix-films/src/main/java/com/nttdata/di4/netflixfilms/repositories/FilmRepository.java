package com.nttdata.di4.netflixfilms.repositories;

import com.nttdata.di4.netflixfilms.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

}
