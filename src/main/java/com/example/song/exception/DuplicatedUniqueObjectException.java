package com.example.song.exception;

import lombok.Getter;

@Getter
public class DuplicatedUniqueObjectException extends Exception {

    Integer errorCode;

    public DuplicatedUniqueObjectException(String errorMessage, Throwable err, Integer errorCode) {
        super(errorMessage, err);
        this.errorCode = errorCode;
    }
}
