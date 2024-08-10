package org.chulgang.hrd.exception;

import java.sql.SQLException;

public class ClosingResultSetFailedException extends SQLException {
    public ClosingResultSetFailedException(String message) {
        super(message);
    }
}
