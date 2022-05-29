package net.bookmarket.exceptions;

public class LowBalanceException extends BookMarketException {

    public LowBalanceException() {
        super();
    }

    public LowBalanceException(String message) {
        super(message);
    }

}
