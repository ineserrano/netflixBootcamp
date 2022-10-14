package com.nttdata.di4.netflixfilms.controllers;

import com.nttdata.di4.netflixfilms.exceptions.NetflixFilmsException;
import com.nttdata.di4.netflixfilms.responses.NetflixFilmsResponse;

public interface FilmController {

    NetflixFilmsResponse<String>testApi() throws NetflixFilmsException;
}
