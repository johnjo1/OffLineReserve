package org.chulgang.hrd.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementGenerator {
    public static Statement generateStatement() {
        try {
            return DbConnection.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement generateStatement(String sql) {
        try {
            return DbConnection.getConnection().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
