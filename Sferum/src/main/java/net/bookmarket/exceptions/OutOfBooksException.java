package net.bookmarket.exceptions;

public class OutOfBooksException extends BookMarketException {

    public OutOfBooksException() {
        super();
    }

    public OutOfBooksException(String message) {
        super(message);
    }

}
