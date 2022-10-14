package com.nttdata.di4.netflixfilms.controllers.impl;

import com.nttdata.di4.netflixfilms.controllers.FilmController;
import com.nttdata.di4.netflixfilms.exceptions.NetflixFilmsException;
import com.nttdata.di4.netflixfilms.responses.NetflixFilmsResponse;
import com.nttdata.di4.netflixfilms.utils.CommonConstants;
import com.nttdata.di4.netflixfilms.utils.RestConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestConstants.RESOURCE_FILMS)
public class FilmControllerImpl implements FilmController {

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixFilmsResponse<String> testApi() throws NetflixFilmsException {
        return new NetflixFilmsResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, "I'm alive!");
    }
}
