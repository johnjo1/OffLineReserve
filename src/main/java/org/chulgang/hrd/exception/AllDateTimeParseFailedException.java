package org.chulgang.hrd.exception;

import java.time.format.DateTimeParseException;

public class AllDateTimeParseFailedException extends DateTimeParseException {
    public AllDateTimeParseFailedException(String message, String parsedData, int errorIndex) {
        super(message, parsedData, errorIndex);
    }
}
