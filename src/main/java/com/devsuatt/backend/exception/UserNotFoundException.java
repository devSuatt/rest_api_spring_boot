package com.devsuatt.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// burada bu class'ın kullanıldığı yere fırlatılacak hatayı annotiation ile belirttik.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    final String message = "-----user not found-----";

    public UserNotFoundException() {}

    @Override
    public String toString() {
        return message;
    }
}
