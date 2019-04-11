package org.itstep;

import java.sql.*;

public class EmployeeRepository {
    private Database db;

    public EmployeeRepository(Database db) {
        this.db = db;
    }

    public Employee create(Employee employee) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        employee.setCreated(timestamp.toString());

        PreparedStatement statement = db
            .getConnection()
            .prepareStatement(
                "INSERT INTO employee (lastname, firstname, position, " +
                "children, birthday, created) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
        statement.setString(1, employee.getLastname());
        statement.setString(2, employee.getFirstname());
        statement.setString(3, employee.getPosition());
        statement.setInt(4, employee.isChildren() ? 1 : 0);
        statement.setDate(
            5,
            null == employee.getBirthday()
                ? null
                : Date.valueOf(employee.getBirthday())
        );
        statement.setTimestamp(6, timestamp);
        statement.executeUpdate();
        ResultSet keys = statement.getGeneratedKeys();

        if (keys.next()) {
            employee.setId(keys.getLong(1));
        }

        statement.close();

        return employee;
    }

    public void create(
        String lastname,
        String firstname,
        String position
    ) throws SQLException {
        create(lastname, firstname, position, false, null);
    }

    public void create(
        String lastname,
        String firstname,
        String position,
        boolean children,
        String birthday
    ) throws SQLException {
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

    public void update(
        int id,
        String lastname,
        String firstname,
        String position,
        boolean children,
        String birthday
    ) throws SQLException {
        PreparedStatement statement = db.prepare(
            "UPDATE employee " +
            "SET lastname = ?, firstname = ?, position = ?, " +
            "children = ?, birthday = ? " +
            "WHERE id = ?"
        );
        statement.setString(1, lastname);
        statement.setString(2, firstname);
        statement.setString(3, position);
        statement.setInt(4, children ? 1 : 0);
        statement.setDate(
            5,
            null == birthday ? null : Date.valueOf(birthday)
        );
        statement.setInt(6, id);
        statement.execute();
        statement.close();
    }

    public void update(
        int id,
        String lastname,
        String firstname,
        String position
    ) throws SQLException {
        update(id, lastname, firstname, position, false, null);
    }

    public void update(
        int id,
        String lastname,
        String firstname
    ) throws SQLException {
        update(id, lastname, firstname, "Разнорабочий", false, null);
    }

    public void delete(int id) throws SQLException {
        PreparedStatement statement = db.prepare(
            "DELETE FROM employee WHERE id = ?"
        );
        statement.setInt(1, id);
        statement.execute();
        statement.close();
    }
}























