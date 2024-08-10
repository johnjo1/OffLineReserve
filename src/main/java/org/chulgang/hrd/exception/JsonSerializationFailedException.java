package org.chulgang.hrd.exception;

import java.io.IOException;

public class JsonSerializationFailedException extends IOException {
    public JsonSerializationFailedException(String message) {
        super(message);
    }
}
