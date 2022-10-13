package com.stefankrstikj.lotterysystem.exception;

public class DateParseException extends RuntimeException {
    public DateParseException(String date) {
        super(String.format("Error parsing date %s. Please use yyyy-mm-dd format", date));
    }
}
