package com.nttdata.di4.netflixfilms.exceptions;

import com.nttdata.di4.netflixfilms.dto.ErrorDto;
import com.nttdata.di4.netflixfilms.utils.ExceptionConstants;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class InternalServerErrorException extends NetflixFilmsException {
    public InternalServerErrorException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstants.INTERNAL_SERVER_ERROR);
    }

    public InternalServerErrorException(final ErrorDto error) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionConstants.INTERNAL_SERVER_ERROR, Arrays.asList(error));
    }
}
