package net.codejava;

import net.codejava.HibernateServices.CustomerService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {


    public static void main(String[] args) {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        CustomerService customerService = new CustomerService();
        customerService.getCustomers(sessionFactory);
    }

//    public static void main(String[] args) throws SQLException, IOException {
//
//        String dbURL = "jdbc:mysql://localhost:3306/uppgift_db_jdbc";
//        String username = "root";
//        String password = "Vfr$3edc";
//
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(dbURL, username, password);
//            if (connection != null) {
//                System.out.println("Connected");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        EmployeeService employeeService = new EmployeeService();
//        OfficeService officeService = new OfficeService();
//        OrderService orderService = new OrderService();
//        ProductService productService = new ProductService();
//        employeeService.getEmployeesFromJDBC(connection);
//    }

}
