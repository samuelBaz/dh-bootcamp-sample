package com.dharbor.sales.exceptions;

public class SaleNotCompletedException extends RuntimeException {

    public SaleNotCompletedException(String message, Throwable cause) {
        super(message, cause);
    }
}
