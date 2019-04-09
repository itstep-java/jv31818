package org.itstep;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRepository {
    private Database db;

    public EmployeeRepository(Database db) {
        this.db = db;
    }

    public void create(String lastname, String firstname, String position) throws SQLException {
        create(lastname, firstname, position, false, null);
    }

    public void create(String lastname, String firstname, String position, boolean children, String birthday) throws SQLException {
        PreparedStatement statement = db.prepare(
            "INSERT INTO employee (lastname, firstname, position, " +
            "children, birthday) VALUES (?, ?, ?, ?, ?)"
        );
        statement.setString(1, lastname);
        statement.setString(2, firstname);
        statement.setString(3, position);
        statement.setInt(4, children ? 1 : 0);
        statement.setDate(5, null == birthday ? null : Date.valueOf(birthday));
        statement.execute();
        statement.close();
    }
}
