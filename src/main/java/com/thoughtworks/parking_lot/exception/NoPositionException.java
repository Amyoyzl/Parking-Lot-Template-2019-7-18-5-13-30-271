package com.thoughtworks.parking_lot.exception;

public class NoPositionException extends Throwable {
    @Override
    public String getMessage() {
        return "停车场已经满.";
    }
}
