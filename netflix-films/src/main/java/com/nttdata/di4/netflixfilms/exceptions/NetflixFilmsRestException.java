package com.nttdata.di4.netflixfilms.exceptions;

import com.nttdata.di4.netflixfilms.responses.NetflixFilmsResponse;
import com.nttdata.di4.netflixfilms.utils.ExceptionConstants;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class NetflixFilmsRestException {

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public NetflixFilmsResponse unhandledErrors(HttpServletRequest request, Exception exception) {
        return new NetflixFilmsResponse(ExceptionConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage());
    }

    @ExceptionHandler({NetflixFilmsException.class})
    @ResponseBody
    public NetflixFilmsResponse handleErrorsException(final HttpServletRequest request, final HttpServletResponse response, final NetflixFilmsException exception) {
        response.setStatus(exception.getCode());
        return new NetflixFilmsResponse(ExceptionConstants.DEFAULT_ERROR, String.valueOf(exception.getCode()), exception.getMessage(), exception.getErrorList());
    }
}
