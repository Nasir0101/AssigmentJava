package net.codejava.hibernate;

import net.codejava.hibernate.DbModels.Employees;
import net.codejava.hibernate.DbModels.Orders;
import net.codejava.hibernate.DbModels.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import sun.swing.BakedArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

    protected void getEmployees() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employees> criteria = builder.createQuery(Employees.class);
        criteria.from(Employees.class);
        List<Employees> entityList = session.createQuery(criteria).getResultList();
        for (Employees e : entityList) {
            System.out.print("  Title: " + e.getFirstName());
            System.out.print("  Author: " + e.getLastName());
            System.out.print("  Price: " + e.getEmail());
            System.out.println();
        }
        session.close();
    }

    protected void createEmployee() {
        Employees employees = new Employees();
        employees.setEmployeeId(10);
        employees.setFirstName("asdas");
        employees.setLastName("afadfs");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employees);
        session.getTransaction().commit();
        session.close();

    }

    protected void updateEmployee() {
        Employees employees = new Employees();
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println(name);
        employees.setLastName(name);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(employees);
        session.getTransaction().commit();
        session.close();
    }

    protected void removeEmployee(int id) {
        Employees employee = new Employees();
        employee.setEmployeeId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }

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
            map.put(i, count != null ? count+1 : 1);
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
        manager.createEmployee();

        manager.exit();
    }

}
