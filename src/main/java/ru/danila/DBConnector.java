package ru.danila;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.sql.*;

/**
 * Class realise connection to database and provide statement for queries
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DBConnector {

    @Getter
    final Statement statement;

    //you should add to environment variables data for DB: url, username, password
    public DBConnector() throws SQLException {
        Driver driver = new org.postgresql.Driver();
        String url = System.getenv().get("url");
        String password = System.getenv().get("password");
        String username = System.getenv().get("username");
        Connection connection = DriverManager.getConnection(url,username,password);
        statement = connection.createStatement();
        DriverManager.registerDriver(driver);
    }
}
