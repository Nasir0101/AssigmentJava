package net.codejava;

import net.codejava.HibernateServices.*;
import net.codejava.JDBCServices.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) throws SQLException, IOException {

        // Jdbc Services objects

        JdbcCustomerService jdbcCustomerService = new JdbcCustomerService();
        JdbcEmployeeService jdbcEmployeeService = new JdbcEmployeeService();
        JdbcOfficeService jdbcOfficeService = new JdbcOfficeService();
        JdbcOrderService jdbcOrderService = new JdbcOrderService();
        JdbcProductService jdbcProductService = new JdbcProductService();

        // Hibernate Services Objects

        CustomerService customerService = new CustomerService();
        EmployeeService employeeService = new EmployeeService();
        OfficeService officeService = new OfficeService();
        OrdersService ordersService = new OrdersService();
        ProductService productService = new ProductService();

        Scanner in = new Scanner(System.in);
        System.out.println("Press 1 for Jdbc and press 2 for Hibernate");
        int option = in.nextInt();

        if (option == 1) {
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

            System.out.println("Select Entity for Operation");

            System.out.println("Press 1 for Customer");
            System.out.println("press 2 for Employee");
            System.out.println("press 3 for Office");
            System.out.println("press 4 for Order");
            System.out.println("press 5 for Product");
            System.out.println("press 6 for gets the customer who ordered most");
            System.out.println("press 7 for gets a list of the products that has a low stock (less than 10)");
            int entityOption = in.nextInt();

            if (entityOption == 1) {
                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        assert connection != null;
                        jdbcCustomerService.getCustomersFromJDBC(connection);
                        break;
                    case "add":
                        assert connection != null;
                        jdbcCustomerService.addCustomerInJDBC(connection);
                        break;
                    case "update":
                        assert connection != null;
                        jdbcCustomerService.updateCustomerInJDBC(connection);
                        break;
                    case "remove":
                        assert connection != null;
                        jdbcCustomerService.removeCustomerFromJDBC(connection);
                        break;
                }
            } else if (entityOption == 2) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        assert connection != null;
                        jdbcEmployeeService.getEmployeesFromJDBC(connection);
                        break;
                    case "add":
                        assert connection != null;
                        jdbcEmployeeService.addEmployeeInJDBC(connection);
                        break;
                    case "update":
                        assert connection != null;
                        jdbcEmployeeService.updateEmployeeInJDBC(connection);
                        break;
                    case "remove":
                        assert connection != null;
                        jdbcEmployeeService.removeEmployeeFromJDBC(connection);
                        break;
                }

            } else if (entityOption == 3) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        assert connection != null;
                        jdbcOfficeService.getOfficeFromJDBC(connection);
                        break;
                    case "add":
                        assert connection != null;
                        jdbcOfficeService.addOfficeInJDBC(connection);
                        break;
                    case "update":
                        assert connection != null;
                        jdbcOfficeService.updateOfficeInJDBC(connection);
                        break;
                    case "remove":
                        assert connection != null;
                        jdbcOfficeService.removeOfficeFromJDBC(connection);
                        break;
                }

            } else if (entityOption == 4) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        assert connection != null;
                        jdbcOrderService.getOrderFromJDBC(connection);
                        break;
                    case "add":
                        assert connection != null;
                        jdbcOrderService.addOrderInJDBC(connection);
                        break;
                    case "update":
                        assert connection != null;
                        jdbcOrderService.updateOrderInJDBC(connection);
                        break;
                    case "remove":
                        assert connection != null;
                        jdbcOrderService.removeOrderFromJDBC(connection);
                        break;
                }

            } else if (entityOption == 5) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        assert connection != null;
                        jdbcProductService.getProductFromJDBC(connection);
                        break;
                    case "add":
                        assert connection != null;
                        jdbcProductService.addProductInJDBC(connection);
                        break;
                    case "update":
                        assert connection != null;
                        jdbcProductService.updateProductInJDBC(connection);
                        break;
                    case "remove":
                        assert connection != null;
                        jdbcProductService.removeProductFromJDBC(connection);
                        break;
                }

            } else if (entityOption == 6) {
                assert connection != null;
                jdbcCustomerService.getCustomerWhoOrderedMost(connection);
            } else if (entityOption == 7) {
                assert connection != null;
                jdbcProductService.StockLessProduct(connection);
            } else {
                System.out.println("Worng Input");
            }


        } else if (option == 2) {


            SessionFactory sessionFactory;
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            System.out.println("Select Entity for Operation");

            System.out.println("Press 1 for Customer");
            System.out.println("press 2 for Employee");
            System.out.println("press 3 for Office");
            System.out.println("press 4 for Order");
            System.out.println("press 5 for Product");
            System.out.println("press 6 for gets the customer who ordered most");
            System.out.println("press 7 for gets a list of the products that has a low stock (less than 10)");
            int entityOption = in.nextInt();

            if (entityOption == 1) {
                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        customerService.getCustomers(sessionFactory);
                        break;
                    case "add":
                        customerService.createCustomer(sessionFactory);
                        break;
                    case "update":
                        System.out.println("Enter id");
                        Integer id = in.nextInt();
                        customerService.updateCustomers(id, sessionFactory);
                        break;
                    case "remove":
                        System.out.println("Enter id");
                        Integer id1 = in.nextInt();
                        customerService.removeCustomer(id1, sessionFactory);
                        break;
                }
            } else if (entityOption == 2) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        employeeService.getEmployees(sessionFactory);
                        break;
                    case "add":
                        employeeService.createEmployee(sessionFactory);
                        break;
                    case "update":
                        System.out.println("Enter id");
                        Integer id = in.nextInt();
                        employeeService.updateEmployee(id,sessionFactory);
                        break;
                    case "remove":
                        System.out.println("Enter id");
                        Integer id1 = in.nextInt();
                        employeeService.removeEmployee(id1,sessionFactory);
                        break;
                }

            } else if (entityOption == 3) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        officeService.getOffices(sessionFactory);
                        break;
                    case "add":
                        officeService.craeteOffice(sessionFactory);
                        break;
                    case "update":
                        System.out.println("Enter id");
                        Integer id = in.nextInt();
                        officeService.updateOffices(id,sessionFactory);
                        break;
                    case "remove":
                        System.out.println("Enter id");
                        Integer id1 = in.nextInt();
                        officeService.removeOffice(id1,sessionFactory);
                        break;
                }

            } else if (entityOption == 4) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        ordersService.getOrders(sessionFactory);
                        break;
                    case "add":
                        ordersService.createOrder(sessionFactory);
                        break;
                    case "update":
                        System.out.println("Enter id");
                        Integer id = in.nextInt();
                        ordersService.updateOrders(id,sessionFactory);
                        break;
                    case "remove":
                        System.out.println("Enter id");
                        Integer id1 = in.nextInt();
                        ordersService.removeOrder(id1,sessionFactory);
                        break;
                }

            } else if (entityOption == 5) {

                System.out.println("Write Command for Opertion : list , add , update , remove ");
                String operation = in.next();

                switch (operation) {
                    case "list":
                        productService.getProducts(sessionFactory);
                        break;
                    case "add":
                        productService.createProducts(sessionFactory);
                        break;
                    case "update":
                        System.out.println("Enter id");
                        Integer id = in.nextInt();
                        productService.updateProducts(id,sessionFactory);
                        break;
                    case "remove":
                        System.out.println("Enter id");
                        Integer id1 = in.nextInt();
                        productService.removeCustomer(id1, sessionFactory);
                        break;
                }

            } else if (entityOption == 6) {

                customerService.customerWithMostOrders(sessionFactory);

            } else if (entityOption == 7) {
                ordersService.lowStock(sessionFactory);
            } else {
                System.out.println("Worng Input");
            }


        } else {

            System.out.println("Wrong Input");
        }


    }


}
