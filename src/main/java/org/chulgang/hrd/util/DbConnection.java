package org.chulgang.hrd.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.chulgang.hrd.util.ConfigConstant.DB_PROPERTY_KEY;
import static org.chulgang.hrd.util.ConfigConstant.TEST_DB_PROPERTY;

public class DbConnection {
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> sqlErrorThreadLocal = ThreadLocal.withInitial(() -> false);

    private static final Logger log = LoggerFactory.getLogger(DbConnection.class);

    private static final String ORACLE_CONNECTION_SUCCESS_MESSAGE = "Oracle 데이터베이스 연결 성공";
    private static final String H2_CONNECTION_SUCCESS_MESSAGE = "H2 데이터베이스 연결 성공";
    private static final String H2_DRIVER_URL = "jdbc:h2:mem:test;MODE=Oracle;DB_CLOSE_DELAY=-1";
    private static final String H2_USERNAME = "sa";
    private static final String H2_PASSWORD = "";


    public static void initialize() {
        String dbMode = System.getProperty(DB_PROPERTY_KEY);

        try {
            if (TEST_DB_PROPERTY.equals(dbMode)) {
                setH2();
                return;
            }

            Connection connection = DatabaseUtil.getDataSource().getConnection();
            connectionThreadLocal.set(connection);

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            connection.setAutoCommit(false);
            log.debug(ORACLE_CONNECTION_SUCCESS_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setH2() throws SQLException {
        Connection connection = DriverManager.getConnection(H2_DRIVER_URL, H2_USERNAME, H2_PASSWORD);
        connectionThreadLocal.set(connection);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        log.debug(H2_CONNECTION_SUCCESS_MESSAGE);
    }

    public static Connection getConnection() {
        initialize();
        return connectionThreadLocal.get();
    }

    public static void setSqlError(boolean sqlError) {
        sqlErrorThreadLocal.set(sqlError);
    }

    public static boolean getSqlError() {
        return sqlErrorThreadLocal.get();
    }

    public static void reset() {
        try {
            Connection connection = connectionThreadLocal.get();
            if (connection != null) {
                if (getSqlError()) {
                    connection.rollback();
                } else {
                    connection.commit();
                }
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionThreadLocal.remove();
            sqlErrorThreadLocal.remove();
        }
    }
}
