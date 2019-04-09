package org.itstep;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(
            "SELECT id, name FROM department0 WHERE id = ?"
        );

        int[] ids = new int[]{1, 2, 3};

        for (int id : ids) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                System.out.println(
                    result.getInt("id") + ": " + result.getString("name")
                );
            }
        }

        connection.close();
    }
}
