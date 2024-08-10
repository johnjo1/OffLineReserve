package org.chulgang.hrd.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSelector {
    public static ResultSet getResultSet(Statement statement, String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;
    }

    public static String[] getEntityData(ResultSet resultSet) {
        try {
            int columnCount = resultSet.getMetaData().getColumnCount();
            String[] data = new String[columnCount];

            if (resultSet.next()) {
                for (int i = 0; i < columnCount; i++) {
                    data[i] = resultSet.getString(i + 1);
                }

                return data;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        return null;
    }
}
