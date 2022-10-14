package com.nttdata.di4.netflixfilms.services.impl;

import com.nttdata.di4.netflixfilms.repositories.FilmRepository;
import com.nttdata.di4.netflixfilms.services.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private ModelMapper modelMapper;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
        this.modelMapper = new ModelMapper();
    }
}
