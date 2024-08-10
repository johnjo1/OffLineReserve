package org.chulgang.hrd.util;

import org.chulgang.hrd.exception.ClosingResultSetFailedException;
import org.chulgang.hrd.exception.ClosingStatementFailedException;
import org.chulgang.hrd.exception.GlobalExceptionHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.chulgang.hrd.exception.ExceptionMessage.CLOSING_RESULTSET_FAILED_EXCEPTION_MESSAGE;
import static org.chulgang.hrd.exception.ExceptionMessage.CLOSING_STATEMENT_FAILED_EXCEPTION_MESSAGE;

public class ConnectionContainer {
    public static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException se) {
            GlobalExceptionHandler.throwCheckedException(
                    new ClosingResultSetFailedException(CLOSING_RESULTSET_FAILED_EXCEPTION_MESSAGE)
            );
        }
    }


    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException se) {
            GlobalExceptionHandler.throwCheckedException(
                    new ClosingStatementFailedException(CLOSING_STATEMENT_FAILED_EXCEPTION_MESSAGE)
            );
        }
    }
}
