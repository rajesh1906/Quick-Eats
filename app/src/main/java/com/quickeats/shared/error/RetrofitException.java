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

    /**
     * Identifies the event kind which triggered a {@link RetrofitException}.
     */
    enum Kind {
        /**
         * A 401 HTTP status code was received from the server.
         */
        AUTHENTICATION,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A 500 HTTP status code was received from the server.
         */
        SERVER,
        /**
         * A 422 HTTP status code was received from the server.
         */
        VALIDATION,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
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

    /**
     * The request URL which produced the error.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Response object containing status code, headers, body, etc.
     */
    public Response getResponse() {
        return response;
    }

    /**
     * The event kind which triggered this error.
     */
    Kind getKind() {
        return kind;
    }

    /**
     * The Retrofit this request was executed on
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

}