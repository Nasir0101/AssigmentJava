package net.codejava.JDBCServices;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class JdbcEmployeeService {


    // Method for getting Employee by jdbc
    public void getEmployeesFromJDBC(Connection connection) throws SQLException {
        String sql = "SELECT * FROM employees;";
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            System.out.print("EmployeeId : " + result.getInt("employee_id") + ",");
            System.out.print(" FirstName : " + result.getString("first_name") + ",");
            System.out.print(" LastName : " + result.getString("last_name") + ",");
            System.out.print(" Email : " + result.getString("email") + ",");
            System.out.print(" Address : " + result.getString("address") + ",");
            System.out.print("Comment : " + result.getString("employee_comment") + ",");
            System.out.println("OfficeId : " + result.getInt("office_id"));

        }
    }

    // Method for add Employee by jdbc
    public void addEmployeeInJDBC(Connection connection) throws IOException, SQLException {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter Employee ID : ");
        int id = in.nextInt();
        System.out.print("Enter First Name : ");
        String firstName = in.next();
        System.out.print("Enter Last Name : ");
        String lastName = in.next();
        System.out.print("Enter Email : ");
        String email = in.next();
        System.out.print("Enter Address : ");
        String address = in.next();
        System.out.print("Enter Comment : ");
        String comment = in.next();
        System.out.print("Enter OfficeId : ");
        int officeId = in.nextInt();

        String sql = "INSERT INTO employees (employee_id, first_name, last_name, email,address,employee_comment,office_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, firstName);
        statement.setString(3, lastName);
        statement.setString(4, email);
        statement.setString(5, address);
        statement.setString(6, comment);
        statement.setInt(7, officeId);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Employee was inserted successfully!");
        }
    }

    // Method for delete Employee in db by jdbc
    public void removeEmployeeFromJDBC(Connection connection) throws SQLException {
        Scanner in  = new Scanner(System.in);
        System.out.print("Enter Employee Id For Delete");
        int id = in.nextInt();

        String sql = "DELETE FROM employees WHERE employee_id=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Employee was deleted successfully!");
        }

    }
    // Method for Updating Employee data in by jdbc
    public void updateEmployeeInJDBC(Connection connection) throws SQLException{
        Scanner in = new Scanner(System.in);
        System.out.print("Enter Employee Id For Check Employee exist : ");
        int id = in.nextInt();
        String sql = "SELECT * from employees where employee_id = " + id;
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if(result.next())
        {
            System.out.print("Enter First Name for update : ");
            String firstName = in.next();
            System.out.print("Enter Last Name for update : ");
            String lastName = in.next();
            System.out.print("Enter Email for update : ");
            String email = in.next();
            System.out.print("Enter Address for update : ");
            String address = in.next();
            System.out.print("Enter Comment for update : ");
            String comment = in.next();
            System.out.print("Enter OfficeId for update : ");
            int officeId = in.nextInt();
            String sqlupdate = "UPDATE employees SET first_name=?, last_name=?, email=?, address=?, employee_comment=?, office_id=? WHERE employee_id=?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlupdate);
            statementUpdate.setString(1, firstName);
            statementUpdate.setString(2, lastName);
            statementUpdate.setString(3, email);
            statementUpdate.setString(4, address);
            statementUpdate.setString(5, comment);
            statementUpdate.setInt(6, officeId);
            statementUpdate.setInt(7, id);
            int rowsUpdated = statementUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Employee was updated successfully!");
            }
        }
        else{
            System.out.print("No Employee Exists against given id");
        }
    }



}
