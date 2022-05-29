package net.bookmarket.exceptions;

public class BookNotFoundException extends BookMarketException {

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }

}
