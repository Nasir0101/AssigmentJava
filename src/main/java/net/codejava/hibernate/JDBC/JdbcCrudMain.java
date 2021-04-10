package net.codejava.hibernate.JDBC;

import java.io.IOException;
import java.sql.*;

public class JdbcCrudMain {

    // Connection Strings
    String dbURL = "jdbc:mysql://localhost:3306/uppgift_db_jdbc";
    String username = "root";
    String password = "Vfr$3edc";
    Connection connection;

    public static void main(String[] args) throws SQLException, IOException {
        JdbcCrudMain jdbcCrud = new JdbcCrudMain();
        jdbcCrud.setUpDbConnection();
    }

    // Database Connection Configuration
    protected void setUpDbConnection() {
        try {
            this.connection = DriverManager.getConnection(dbURL, username, password);
            if (this.connection != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method for closing jdbc Connection
    protected void closeConnection() throws SQLException {
        this.connection.close();
    }











}
