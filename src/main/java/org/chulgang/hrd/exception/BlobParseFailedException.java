package org.chulgang.hrd.exception;

import java.sql.SQLException;

public class BlobParseFailedException extends SQLException {
    public BlobParseFailedException(String message) {
        super(message);
    }
}
