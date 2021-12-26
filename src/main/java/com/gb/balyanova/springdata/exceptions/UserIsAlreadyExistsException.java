package com.gb.balyanova.springdata.exceptions;

public class UserIsAlreadyExistsException extends Throwable {
    public UserIsAlreadyExistsException(String msg) {
        super(msg);
    }
}
