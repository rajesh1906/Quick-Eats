package com.quickeats.shared.error;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;


public class RetrofitException extends RuntimeException {

    public static RetrofitException authenticationError(Response response, Retrofit retrofit) {
        return new RetrofitException("Authentication failed", null, response, Kind.AUTHENTICATION, null, retrofit);
    }

    public static RetrofitException serverError(Response response, Retrofit retrofit) {
        return new RetrofitException("Server error", null, response, Kind.SERVER, null, retrofit);
    }

    public static RetrofitException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RetrofitException(message, url, response, Kind.HTTP, null, retrofit);
    }

    public static RetrofitException networkError(IOException exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.NETWORK, exception, null);
    }

    public static RetrofitException validationError(Response response, Retrofit retrofit) {
        return new RetrofitException("Validation failed", null, response, Kind.VALIDATION, null, retrofit);
    }

    public static RetrofitException unexpectedError(Throwable exception) {
        return new RetrofitException(exception.getMessage(), null, null, Kind.UNEXPECTED, exception, null);
    }


    enum Kind {
        AUTHENTICATION,
        HTTP,
        NETWORK,
        SERVER,
        VALIDATION,
        UNEXPECTED
    }

    private final String url;
    private final Response response;
    private final Kind kind;
    private final Retrofit retrofit;

    private RetrofitException(String message, String url, Response response, Kind kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }
    Kind getKind() {
        return kind;
    }

}