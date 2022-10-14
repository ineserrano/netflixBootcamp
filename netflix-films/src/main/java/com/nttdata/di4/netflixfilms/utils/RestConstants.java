package com.nttdata.di4.netflixfilms.utils;

public class RestConstants {
    public static final String APPLICATION_NAME = "/netflix-films";
    public static final String API_VERSION_1 = "/v1";
    public static final String SUCCESS = "Success";

    public static final String RESOURCE_FILMS = "/films";

    public static final String RESOURCE_ID = "/{id}";

    public static final String PARAMETER_FILM = "films";


    private RestConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
