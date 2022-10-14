package com.nttdata.di4.netflixfilms.exceptions;

import com.nttdata.di4.netflixfilms.dto.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotFoundException extends NetflixFilmsException {
    private static final long serialVersionUID = -2943376698746355848L;

    public NotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND.value(), message);
    }

    public NotFoundException(final String message, final ErrorDto error) {
        super(HttpStatus.NOT_FOUND.value(), message, Arrays.asList(error));
    }
}
