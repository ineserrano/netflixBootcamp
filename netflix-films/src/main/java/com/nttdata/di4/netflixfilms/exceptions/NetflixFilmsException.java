package com.nttdata.di4.netflixfilms.exceptions;

import com.nttdata.di4.netflixfilms.dto.ErrorDto;

import java.util.ArrayList;
import java.util.List;

public class NetflixFilmsException extends Exception {

    private static final long serialVersionUID = -750227145818694831L;

    private final int code;

    private final List<ErrorDto> errorList = new ArrayList<>();


    public NetflixFilmsException(int code, final String message) {
        super(message);
        this.code = code;
    }

    public NetflixFilmsException(int code, final String message, final List<ErrorDto> errorList) {
        super(message);
        this.code = code;
        this.errorList.addAll(errorList);
    }

    public int getCode() {
        return code;
    }

    public List<ErrorDto> getErrorList() {
        return errorList;
    }
}
