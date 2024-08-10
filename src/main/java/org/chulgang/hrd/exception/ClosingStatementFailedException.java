package org.chulgang.hrd.exception;

import java.sql.SQLException;

public class ClosingStatementFailedException extends SQLException {
    public ClosingStatementFailedException(String message) {
        super(message);
    }
}
