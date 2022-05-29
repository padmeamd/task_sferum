package net.bookmarket.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public abstract class BookMarketException extends RuntimeException {

    public BookMarketException() {
        super();
    }

    public BookMarketException(String message) {
        super(message);
    }
}
