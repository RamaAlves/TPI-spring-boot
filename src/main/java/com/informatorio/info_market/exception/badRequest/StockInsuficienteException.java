package com.informatorio.info_market.exception.badRequest;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }

    public StockInsuficienteException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockInsuficienteException(Throwable cause) {
        super(cause);
    }

    public StockInsuficienteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StockInsuficienteException() {
    }
}
