package com.backend.iffatv.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(Long id) {
        super("Movie not found with ID: " + id);
    }
}
