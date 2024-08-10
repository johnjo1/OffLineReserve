package org.chulgang.hrd.util;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class DatabaseUtil {
    public DatabaseUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final DataSource DATASOURCE;

    static {
        Properties properties = new Properties();
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IOException("database.properties 파일을 찾을 수 없습니다");
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("database.properties load를 실패했습니다.", ex);
        }

        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(properties.getProperty("db.driverClassName"));
        basicDataSource.setUrl(properties.getProperty("db.url"));
        basicDataSource.setUsername(properties.getProperty("db.username"));
        basicDataSource.setPassword(properties.getProperty("db.password"));

        basicDataSource.setInitialSize(20);
        basicDataSource.setMaxTotal(20);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMinIdle(20);

        basicDataSource.setMaxWaitMillis(Duration.ofSeconds(10).toMillis());
        basicDataSource.setValidationQuery("SELECT 1 FROM DUAL");
        basicDataSource.setTestOnBorrow(true);

        DATASOURCE = basicDataSource;
    }

    public static DataSource getDataSource() {
        return DATASOURCE;
    }
}
