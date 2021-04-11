package net.codejava.JDBCServices;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JdbcOrderService {


    // Get Order from Db by JDBC
    public void getOrderFromJDBC(Connection connection) throws SQLException {
        String sql = "SELECT * FROM orders;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.print("order_id : " + result.getInt("order_id") + ",");
            System.out.print(" product_id : " + result.getInt("product_id") + ",");
            System.out.print(" customer_id : " + result.getInt("customer_id"));
            System.out.print("amount : " + result.getInt("amount") + ",");
            System.out.print(" order_date : " + result.getDate("order_date") + ",");
            System.out.print(" requested_shipping_date : " + result.getDate("requested_shipping_date") + ",");
            System.out.println(" shipping_date : " + result.getDate("shipping_date"));
        }
    }


    //  remove order from db by jdbc
    public void removeOrderFromJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Order Id For Delete");
        int order_id = in.nextInt();
        String sql = "DELETE FROM orders WHERE order_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, order_id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Order was deleted successfully!");
        }
    }

    // add order in db by jdbc
    public void addOrderInJDBC(Connection connection) throws IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter order_id : ");
        int order_id = in.nextInt();
        System.out.print("Enter product_id : ");
        int product_id = in.nextInt();
        System.out.print("Enter customer_id : ");
        int customer_id = in.nextInt();
        System.out.print("Enter amount : ");
        int amount = in.nextInt();
        System.out.print("Enter order_date (yyyy-mm-dd) : ");
        String order_date = in.next();
        System.out.print("Enter requested_shipping_date (yyyy-mm-dd): ");
        String requested_shipping_date = in.next();
        System.out.print("Enter shipping_date (yyyy-mm-dd): ");
        String shipping_date = in.next();

        String sql = "INSERT INTO orders (order_id, product_id, customer_id, amount,order_date,requested_shipping_date,shipping_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, order_id);
        statement.setInt(2, product_id);
        statement.setInt(3, customer_id);
        statement.setInt(4, amount);
        statement.setDate(5, Date.valueOf(order_date));
        statement.setDate(6, Date.valueOf(requested_shipping_date));
        statement.setDate(7, Date.valueOf(shipping_date));
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Order was inserted successfully!");
        }
    }

    // Method for Updating Order data in by jdbc
    public void updateOrderInJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Order Id For Check Order exist : ");
        int id = in.nextInt();
        String sql = "SELECT * from orders where order_id = " + id;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            System.out.print("Enter product_id for update: ");
            int product_id = in.nextInt();
            System.out.print("Enter customer_id for update : ");
            int customer_id = in.nextInt();
            System.out.print("Enter amount for update : ");
            int amount = in.nextInt();
            System.out.print("Enter order_date (yyyy-mm-dd) : ");
            String order_date = in.next();
            System.out.print("Enter requested_shipping_date (yyyy-mm-dd): ");
            String requested_shipping_date = in.next();
            System.out.print("Enter shipping_date (yyyy-mm-dd): ");
            String shipping_date = in.next();
            String sqlupdate = "UPDATE orders SET product_id=?, customer_id=?, amount=?, order_date=?, requested_shipping_date=?, shipping_date=? WHERE order_id=?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlupdate);
            statementUpdate.setInt(1, product_id);
            statementUpdate.setInt(2, customer_id);
            statementUpdate.setInt(3, amount);
            statementUpdate.setDate(4, Date.valueOf(order_date));
            statementUpdate.setDate(5, Date.valueOf(requested_shipping_date));
            statementUpdate.setDate(6, Date.valueOf(shipping_date));
            statementUpdate.setInt(7, id);
            int rowsUpdated = statementUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Order was updated successfully!");
            }
        } else {
            System.out.print("No Order Exists against given id");
        }
    }
}
