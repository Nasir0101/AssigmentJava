package net.codejava.hibernate;

import net.codejava.hibernate.DbModels.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import sun.swing.BakedArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.*;


public class HibernateManager {
    protected SessionFactory sessionFactory;

    protected void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    protected void exit() {
        sessionFactory.close();
    }

    protected void create() {
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPrice(32.59f);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    protected void read() {
        Session session = sessionFactory.openSession();
        long bookId = 20;
        Book book = session.get(Book.class, bookId);
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Price: " + book.getPrice());

        session.close();
    }

    //Getting all the employees from the database
    protected void getEmployees() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employees> criteria = builder.createQuery(Employees.class);
        criteria.from(Employees.class);
        List<Employees> entityList = session.createQuery(criteria).getResultList();
        for (Employees e : entityList) {
            System.out.print("  FirstName: " + e.getFirstName() +"  LastName: " + e.getLastName()+"  Email: " + e.getEmail() +"  Address: " + e.getAddress() +"  EmployeeComment: " + e.getEmployeeComment() +"  OfficeId: " + e.getOfficeId());
        }
        session.close();
    }

    //Getting all order list form the database
    protected void getOrders() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> criteria = builder.createQuery(Orders.class);
        criteria.from(Orders.class);
        List<Orders> entityList = session.createQuery(criteria).getResultList();
        for (Orders e : entityList) {
            System.out.print("  OrderId: " + e.getOrderId()+"  Amount: " + e.getAmount() +"  CustomerId: " + e.getCustomerId() + "  Order Date: " + e.getOrderDate() +"  Shipping Date: " + e.getShippingDate() +"  RequestedShippingDate: " + e.getRequestedShippingDate());
            System.out.println();
        }
        session.close();
    }

    //Getting all customers from the database
    protected void getCustomers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customers> criteria = builder.createQuery(Customers.class);
        criteria.from(Customers.class);
        List<Customers> entityList = session.createQuery(criteria).getResultList();
        for (Customers e : entityList) {
            System.out.print("  Customer Id : " + e.getCustomerId() +"  FirstName : " + e.getFirstName() +"  LastName : " + e.getLastName() +"  Email : : " + e.getEmail() +"  Customer Comment : " + e.getCustomerComment());
        }
        session.close();
    }

    //Getting all offices list from the database
    protected void getOffices() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Offices> criteria = builder.createQuery(Offices.class);
        criteria.from(Offices.class);
        List<Offices> entityList = session.createQuery(criteria).getResultList();
        for (Offices e : entityList) {
            System.out.print("  Office Id: " + e.getOfficeId() + "  Office Name: " + e.getOfficeName() + "  Office address : " + e.getAddress() );
        }
        session.close();
    }

    //Create a new employeee, user provide all the fields
    protected void createEmployee() {
        Employees employees = new Employees();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee id");
        int id = scanner.nextInt();
        employees.setEmployeeId(id);
        System.out.println("Enter Employee name");
        String name = scanner.next();
        employees.setFirstName(name);
        System.out.println("Enter Employee lname");
        String lname = scanner.next();
        employees.setLastName(lname);
        System.out.println("Enter Employee email");
        String email = scanner.next();
        employees.setEmail(email);
        System.out.println("Enter Employee address");
        String address = scanner.next();
        employees.setAddress(address);
        System.out.println("Enter Employee comment");
        String comment = scanner.next();
        employees.setEmployeeComment(comment);
        System.out.println("Enter Employee officeId");
        String officeId = scanner.next();
        employees.setEmployeeComment(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employees);
        session.getTransaction().commit();
        session.close();
    }
    //Create a new office, user provide all the fields
    protected void craeteOffice() {
        Offices offices = new Offices();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter office id");
        int id = scanner.nextInt();
        offices.setOfficeId(id);
        System.out.println("Enter office name");
        String name = scanner.next();
        offices.setOfficeName(name);
        System.out.println("Enter office address");
        String address = scanner.next();
        offices.setAddress(address);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(offices);
        session.getTransaction().commit();
        session.close();
    }
    //Create a new Product, user provide all the fields
    protected void createProducts() {
        Products products = new Products();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Products id");
        int id = scanner.nextInt();
        products.setProductId(id);
        System.out.println("Enter Products name");
        String name = scanner.next();
        products.setProductName(name);
        System.out.println("Enter Products description");
        String description = scanner.next();
        products.setProductDescription(description);
        System.out.println("Enter Products price");
        BigDecimal ProductPrice = scanner.nextBigDecimal();
        products.setProductPrice(ProductPrice);
        System.out.println("Enter Products stockAmount");
        Integer stockAmount = scanner.nextInt();
        products.setStockAmount(stockAmount);
        System.out.println("Enter Products warehouseId");
        String warehouseId = scanner.next();
        products.setWarehouseId(warehouseId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(products);
        session.getTransaction().commit();
        session.close();
    }

    //Create a new order, user provide all the fields
    protected void createOrder() {
        Orders orders = new Orders();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter OrderId");
        int id = scanner.nextInt();
        orders.setOrderId(id);
        System.out.println("Enter order amount");
        Integer amount = scanner.nextInt();
        orders.setAmount(amount);
        System.out.println("Enter order customerId");
        Integer customerId = scanner.nextInt();
        orders.setCustomerId(customerId);
        //System.out.println("Enter Employee RequestedShippingDate(");
        //java.sql.Date = new Date();
        orders.setRequestedShippingDate(null);
        System.out.println("Enter order date");
        String address = scanner.next();
        orders.setOrderDate(null);
        System.out.println("Enter order comment");
        String comment = scanner.next();
        orders.setShippingDate(null);
        System.out.println("Enter order officeId");
        Integer officeId = scanner.nextInt();
        orders.setProductId(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(orders);
        session.getTransaction().commit();
        session.close();
    }

    //for updating a employee , user will provide id
    protected void updateEmployee(int id) {
        Employees employees = new Employees();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Employee name");
        String name = scanner.next();
        employees.setFirstName(name);
        System.out.println("Enter Employee last name");
        String lname = scanner.next();
        employees.setLastName(lname);
        System.out.println("Enter Employee email");
        String email = scanner.next();
        employees.setEmail(email);
        System.out.println("Enter Employee address");
        String address = scanner.next();
        employees.setAddress(address);
        System.out.println("Enter Employee comment");
        String comment = scanner.next();
        employees.setEmployeeComment(comment);
        System.out.println("Enter Employee officeId");
        String officeId = scanner.next();
        employees.setEmployeeComment(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(employees);
        session.getTransaction().commit();
        session.close();
    }

    //for updating a product , user will provide id
    protected void updateProducts(int id) {
        Products products = new Products();
        Scanner scanner = new Scanner(System.in);
        products.setProductId(id);
        System.out.println("Enter Products name");
        String name = scanner.next();
        products.setProductName(name);
        System.out.println("Enter Products description");
        String description = scanner.next();
        products.setProductDescription(description);
        System.out.println("Enter Products price");
        BigDecimal ProductPrice = scanner.nextBigDecimal();
        products.setProductPrice(ProductPrice);
        System.out.println("Enter Products stockAmount");
        Integer stockAmount = scanner.nextInt();
        products.setStockAmount(stockAmount);
        System.out.println("Enter Products warehouseId");
        String officeId = scanner.next();
        products.setWarehouseId(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(products);
        session.getTransaction().commit();
        session.close();
    }

    //for updating a office , user will provide id
    protected void updateOffices(int id){
        Offices offices = new Offices();
        Scanner scanner = new Scanner(System.in);
        offices.setOfficeId(id);
        System.out.println("Enter office name");
        String name = scanner.next();
        offices.setOfficeName(name);
        System.out.println("Enter office address");
        String address = scanner.next();
        offices.setAddress(address);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(offices);
        session.getTransaction().commit();
        session.close();
    }

    //for updating a order , user will provide id
    protected void updateOrders(int id){
        Orders orders = new Orders();
        Scanner scanner = new Scanner(System.in);
        orders.setOrderId(id);
        System.out.println("Enter Employee name");
        Integer amount = scanner.nextInt();
        orders.setAmount(amount);
        System.out.println("Enter Employee customerId");
        Integer customerId = scanner.nextInt();
        orders.setCustomerId(customerId);
        //System.out.println("Enter Employee email");
        //java.sql.Date = new Date();
        orders.setRequestedShippingDate(null);
        System.out.println("Enter Employee address");
        String address = scanner.next();
        orders.setOrderDate(null);
        System.out.println("Enter Employee comment");
        String comment = scanner.next();
        orders.setShippingDate(null);
        System.out.println("Enter Employee officeId");
        Integer officeId = scanner.nextInt();
        orders.setProductId(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(orders);
        session.getTransaction().commit();
        session.close();
    }

    //for deleting a customer , user will provide id
    protected void removeCustomer(int id) {
        Customers customer = new Customers();
        customer.setCustomerId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }
    //for deleting a order , user will provide id
    protected void removeOrder(int id) {
        Orders order = new Orders();
        order.setOrderId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(order);
        session.getTransaction().commit();
        session.close();
    }
    //for deleting a office , user will provide id
    protected void removeOffice(int id) {
        Offices offices = new Offices();
        offices.setOfficeId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(offices);
        session.getTransaction().commit();
        session.close();
    }
    //for deleting a employee , user will provide id
    protected void removeEmployee(int id) {
        Employees employee = new Employees();
        employee.setEmployeeId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }

    //ToCheck stock of the products  , stock low if products is less than 10
    protected void lowStock() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Products> criteria = builder.createQuery(Products.class);
        criteria.from(Employees.class);
        List<Products> entityList = session.createQuery(criteria).getResultList();
        for (Products e : entityList) {
            if (e.getStockAmount() < 10) {
            }
            System.out.println("Product " + e.getProductDescription() + " is low in stock");
        }
        session.close();

    }

    //To find customer with most orders
    protected void customerWithMostOrders() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> criteria = builder.createQuery(Orders.class);
        criteria.from(Orders.class);
        List<Orders> entityList = session.createQuery(criteria).getResultList();
        List<Integer> customerIdsFromOrders = new ArrayList();
        for (Orders e : entityList) {
            customerIdsFromOrders.add(e.getCustomerId());
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i : customerIdsFromOrders) {
            Integer count = map.get(i);
            map.put(i, count != null ? count + 1 : 1);
        }
        session.close();


    }

    protected void update() {
        Book book = new Book();
        book.setId(20);
        book.setTitle("Ultimate Java Programming");
        book.setAuthor("Nam Ha Minh");
        book.setPrice(19.99f);

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(book);

        session.getTransaction().commit();
        session.close();
    }

    protected void delete() {
        Book book = new Book();
        book.setId(20);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(book);
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        HibernateManager manager = new HibernateManager();
        manager.setup();
        //manager.create();
        //manager.read();
        // manager.createEmployee();

        manager.exit();
    }

}
