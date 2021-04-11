package net.codejava.hibernate.JDBC;

import net.codejava.hibernate.JDBC.Services.EmployeeService;
import net.codejava.hibernate.JDBC.Services.OfficeService;
import net.codejava.hibernate.JDBC.Services.OrderService;
import net.codejava.hibernate.JDBC.Services.ProductService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcCrudMain {


    public static void main(String[] args) throws SQLException, IOException {

        String dbURL = "jdbc:mysql://localhost:3306/uppgift_db_jdbc";
        String username = "root";
        String password = "Vfr$3edc";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            if (connection != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        EmployeeService employeeService = new EmployeeService();
        OfficeService officeService = new OfficeService();
        OrderService orderService = new OrderService();
        ProductService productService = new ProductService();
        employeeService.getEmployeesFromJDBC(connection);
    }


}
