package com.example.song.exception;

import lombok.Getter;

/**
 * Excepción que es lanzada al introducir un objeto cuya regla 'unique' ha sido violada.
 */
@Getter
public class DuplicatedUniqueObjectException extends Exception {

    Integer errorCode;

    public DuplicatedUniqueObjectException(String errorMessage, Throwable err, Integer errorCode) {
        super(errorMessage, err);
        this.errorCode = errorCode;
    }
}
