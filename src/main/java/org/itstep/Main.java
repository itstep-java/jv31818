package org.itstep;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = new Database();
        EmployeeRepository repository = new EmployeeRepository(db);
        repository.create("Иванов", "Иван", "CIO");
        db.close();
    }
}
