package net.codejava.hibernate.JDBC.Services;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class OfficeService {


    //Method for get Offices from db by jdbc
    public void getOfficeFromJDBC(Connection connection) throws SQLException {
        String sql = "SELECT * FROM offices;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.print("office_id : " + result.getInt("office_id") + ",");
            System.out.print(" office_name : " + result.getString("office_name") + ",");
            System.out.println(" address : " + result.getString("address"));


        }
    }

    // Method for add Office data in db by jdbc
    public void addOfficeInJDBC(Connection connection) throws IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Office ID : ");
        int office_id = in.nextInt();
        System.out.print("Enter office_name : ");
        String office_name = in.next();
        System.out.print("Enter address : ");
        String address = in.next();
        String sql = "INSERT INTO offices (office_id, office_name, address) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, office_id);
        statement.setString(2, office_name);
        statement.setString(3, address);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Office was inserted successfully!");
        }
    }

    public void removeOfficeFromJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Office Id For Delete");
        int office_id = in.nextInt();
        String sql = "DELETE FROM offices WHERE office_id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, office_id);
        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Office was deleted successfully!");
        }
    }

    // Method for Updating Office data in by jdbc
    public void updateOfficeInJDBC(Connection connection) throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Office Id For Check office exist : ");
        int id = in.nextInt();
        String sql = "SELECT * from offices where office_id = " + id;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            System.out.print("Enter Office ID for update : ");
            int office_id = in.nextInt();
            System.out.print("Enter office_name for update : ");
            String office_name = in.next();
            System.out.print("Enter address for update: ");
            String address = in.next();
            String sqlupdate = "UPDATE offices SET office_name=?, address=? WHERE office_id=?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlupdate);
            statementUpdate.setString(1, office_name);
            statementUpdate.setString(2, address);
            statementUpdate.setInt(3, id);
            int rowsUpdated = statementUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Office was updated successfully!");
            }
        } else {
            System.out.print("No Office Exists against given id");
        }
    }

}
