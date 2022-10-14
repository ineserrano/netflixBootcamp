package com.di4.bootcamp.subscriptions.utils.constants;

public class ExceptionConstants {

    public static final String ERROR = "An error has occurred.";
    public static final String INTERNET_SERVER_ERROR = "An unexpected error has occurred.";
    public static final String NO_CHANGES_DETECTED_ERROR = "There were detected any changes in the resource.";

    private ExceptionConstants() {
        throw new IllegalStateException("Utility Class");
    }
}
