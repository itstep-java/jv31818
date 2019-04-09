package org.itstep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String host = "remotemysql.com";
    private final int port = 3306;
    private final String database = "jijjN3qVrQ";
    private final String username = "jijjN3qVrQ";
    private final String password = "UDi2DTvyg7";

    private Connection connection;

    public Connection getConnection() throws SQLException {
        if (null == connection) {
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + host + ":" + port + "/" + database,
                username,
                password
            );
        }

        return connection;
    }
}
