package net.codejava.JDBCServices;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JdbcCustomerService {

    // Method for getting Customer by jdbc
    public void getCustomersFromJDBC(Connection connection) throws SQLException {
        String sql = "SELECT * FROM customers;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.print("customer_id : " + result.getInt("customer_id") + ",");
            System.out.print(" first_name : " + result.getString("first_name") + ",");
            System.out.print(" last_name : " + result.getString("last_name") + ",");
            System.out.print(" email : " + result.getString("email") + ",");
            System.out.print(" address : " + result.getString("address") + ",");
            System.out.print("customer_comment : " + result.getString("customer_comment") + ",");
            System.out.print("organisation_id : " + result.getInt("organisation_id"));
            System.out.println("discount_group : " + result.getInt("discount_group"));

        }
    }


    // Method for Customer Employee data in by jdbc
    public void updateCustomerInJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Customer Id For Check Customer exist : ");
        int id = in.nextInt();
        String sql = "SELECT * from customers where customer_id = " + id;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            System.out.print("Enter First Name for update : ");
            String first_name = in.next();
            System.out.print("Enter Last Name for update : ");
            String last_name = in.next();
            System.out.print("Enter Email for update : ");
            String email = in.next();
            System.out.print("Enter Address for update : ");
            String address = in.next();
            System.out.print("Enter Comment for update : ");
            String customer_comment = in.next();
            System.out.print("Enter discount group for update : ");
            int discount_group = in.nextInt();
            String sqlupdate = "UPDATE customers SET first_name=?, last_name=?, email=?, address=?, customer_comment=?, discount_group=? WHERE customer_id=?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlupdate);
            statementUpdate.setString(1, first_name);
            statementUpdate.setString(2, last_name);
            statementUpdate.setString(3, email);
            statementUpdate.setString(4, address);
            statementUpdate.setString(5, customer_comment);
            statementUpdate.setInt(6, discount_group);
            statementUpdate.setInt(7, id);
            int rowsUpdated = statementUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Customer was updated successfully!");
            }
        } else {
            System.out.print("No Customer Exists against given id");
        }
    }

    // Method for delete Customer in db by jdbc
    public void removeCustomerFromJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Customer Id For customers");
        int id = in.nextInt();

        String sql = "DELETE FROM customers WHERE customer_id=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Customer was deleted successfully!");
        }

    }

    // Method for add Customer by jdbc
    public void addCustomerInJDBC(Connection connection) throws IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Customer ID : ");
        int customer_id = in.nextInt();
        System.out.print("Enter First Name : ");
        String first_name = in.next();
        System.out.print("Enter Last Name : ");
        String last_name = in.next();
        System.out.print("Enter Email : ");
        String email = in.next();
        System.out.print("Enter Address : ");
        String address = in.next();
        System.out.print("Enter Comment : ");
        String customer_comment = in.next();
        System.out.print("Enter Discount Group ID : ");
        int discount_group = in.nextInt();

        String sql = "INSERT INTO customers (customer_id, first_name, last_name, email,address,customer_comment,discount_group) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, customer_id);
        statement.setString(2, first_name);
        statement.setString(3, last_name);
        statement.setString(4, email);
        statement.setString(5, address);
        statement.setString(6, customer_comment);
        statement.setInt(7, discount_group);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Customer was inserted successfully!");
        }
    }

    // Method for getting customer id who ordered Most
    public void getCustomerWhoOrderedMost(Connection connection) throws SQLException {
        String sql = "SELECT customer_id, count(customer_id) as count FROM uppgift_db_jdbc.orders group by customer_id order by count desc limit 1;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.println("Customer Id who Orderd Most : " + result.getInt("customer_id"));
        }
    }
}
