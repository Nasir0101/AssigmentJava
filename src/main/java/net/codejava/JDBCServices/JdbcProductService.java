package net.codejava.JDBCServices;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Scanner;

public class JdbcProductService {

    //Method for getting product data by jdbc
    public void getProductFromJDBC(Connection connection) throws SQLException {
        String sql = "SELECT * FROM products;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.print("product_id : " + result.getInt("product_id") + ",");
            System.out.print(" product_name : " + result.getString("product_name") + ",");
            System.out.print(" product_description : " + result.getString("product_description") + ",");
            System.out.print(" product_price : " + result.getBigDecimal("product_price") + ",");
            System.out.print(" stock_amount : " + result.getInt("stock_amount") + ",");
            System.out.println("warehouse_id : " + result.getString("warehouse_id"));

        }
    }

    //Method for add product in db by jdbc
    public void addProductInJDBC(Connection connection) throws IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter product_id : ");
        int product_id = in.nextInt();
        System.out.print("Enter product_name : ");
        String product_name = in.next();
        System.out.print("Enter product_description : ");
        String product_description = in.next();
        System.out.print("Enter product_price : ");
        BigDecimal product_price = in.nextBigDecimal();
        System.out.print("Enter stock_amount : ");
        int stock_amount = in.nextInt();
        System.out.print("Enter warehouse_id : ");
        String warehouse_id = in.next();


        String sql = "INSERT INTO products (product_id, product_name, product_description, product_price,stock_amount,warehouse_id) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, product_id);
        statement.setString(2, product_name);
        statement.setString(3, product_description);
        statement.setBigDecimal(4, product_price);
        statement.setInt(5, stock_amount);
        statement.setString(6, warehouse_id);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Product was inserted successfully!");
        }
    }

    // Remove product data from db by jdbc
    public void removeProductFromJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Product Id For Delete");
        int id = in.nextInt();

        String sql = "DELETE FROM products WHERE product_id=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Product was deleted successfully!");
        }

    }

    // Method for Updating Product data in by jdbc
    public void updateProductInJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Product Id For Check Product exist : ");
        int id = in.nextInt();
        String sql = "SELECT * from products where product_id = " + id;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            System.out.print("Enter product_name for update : ");
            String product_name = in.next();
            System.out.print("Enter product_description for update : ");
            String product_description = in.next();
            System.out.print("Enter product_price for update : ");
            BigDecimal product_price = in.nextBigDecimal();
            System.out.print("Enter stock_amount for update : ");
            int stock_amount = in.nextInt();
            System.out.print("Enter warehouse_id for update : ");
            String warehouse_id = in.next();
            String sqlupdate = "UPDATE products SET product_name=?, product_description=?, product_price=?, stock_amount=?, warehouse_id=? WHERE product_id=?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlupdate);
            statementUpdate.setString(1, product_name);
            statementUpdate.setString(2, product_description);
            statementUpdate.setBigDecimal(3, product_price);
            statementUpdate.setInt(4, stock_amount);
            statementUpdate.setString(5, warehouse_id);
            statementUpdate.setInt(6, id);
            int rowsUpdated = statementUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing product was updated successfully!");
            }
        } else {
            System.out.print("No product Exists against given id");
        }
    }

    //  Mehtod for getting product name who have less stock under 10
    public void StockLessProduct(Connection connection) throws SQLException {
        String sql = "SELECT * FROM uppgift_db_jdbc.products where stock_amount < 10;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.println("Product Name" + result.getInt("product_name"));
        }
    }

}
