package org.itstep;

import java.sql.*;

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

    public void close() throws SQLException {
        if (null != connection && !connection.isClosed()) {
            connection.close();
        }
    }

    public void execute(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.execute(query);
        statement.close();
    }

    public PreparedStatement prepare(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }
}
