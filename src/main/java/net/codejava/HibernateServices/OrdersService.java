package net.codejava.HibernateServices;

import net.codejava.DbModels.Employees;
import net.codejava.DbModels.Orders;
import net.codejava.DbModels.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class OrdersService {


    //Getting all order list form the database
    public void getOrders(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Orders> criteria = builder.createQuery(Orders.class);
        criteria.from(Orders.class);
        List<Orders> entityList = session.createQuery(criteria).getResultList();
        for (Orders e : entityList) {
            System.out.print("  OrderId: " + e.getOrderId() + "  Amount: " + e.getAmount() + "  CustomerId: " + e.getCustomerId() + "  Order Date: " + e.getOrderDate() + "  Shipping Date: " + e.getShippingDate() + "  RequestedShippingDate: " + e.getRequestedShippingDate());
            System.out.println();
        }
        session.close();
    }

    //Create a new order, user provide all the fields
    public void createOrder(SessionFactory sessionFactory) {
        Orders orders = new Orders();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter OrderId");
        int id = scanner.nextInt();
        orders.setOrderId(id);
        System.out.println("Enter Employee name");
        Integer amount = scanner.nextInt();
        orders.setAmount(amount);
        System.out.println("Enter Employee customerId");
        Integer customerId = scanner.nextInt();
        orders.setCustomerId(customerId);
        System.out.print("Enter shipping_date (yyyy-mm-dd): ");
        String RequestedShippingDate = scanner.next();
        orders.setRequestedShippingDate(Date.valueOf(RequestedShippingDate));
        System.out.print("Enter orderDate (yyyy-mm-dd): ");
        String orderDate = scanner.next();
        orders.setOrderDate(Date.valueOf(orderDate));
        System.out.print("Enter orderDate (yyyy-mm-dd): ");
        String shippingDate = scanner.next();
        orders.setShippingDate(Date.valueOf(shippingDate));
        System.out.println("Enter Employee officeId");
        Integer officeId = scanner.nextInt();
        orders.setProductId(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(orders);
        session.getTransaction().commit();
        session.close();
    }

    //for updating a order , user will provide id
    public void updateOrders(int id, SessionFactory sessionFactory) {
        Orders orders = new Orders();
        Scanner scanner = new Scanner(System.in);
        orders.setOrderId(id);
        System.out.println("Enter Employee name");
        Integer amount = scanner.nextInt();
        orders.setAmount(amount);
        System.out.println("Enter Employee customerId");
        Integer customerId = scanner.nextInt();
        orders.setCustomerId(customerId);
        System.out.print("Enter shipping_date (yyyy-mm-dd): ");
        String RequestedShippingDate = scanner.next();
        orders.setRequestedShippingDate(Date.valueOf(RequestedShippingDate));
        System.out.print("Enter orderDate (yyyy-mm-dd): ");
        String orderDate = scanner.next();
        orders.setOrderDate(Date.valueOf(orderDate));
        System.out.print("Enter orderDate (yyyy-mm-dd): ");
        String shippingDate = scanner.next();
        orders.setShippingDate(Date.valueOf(shippingDate));
        System.out.println("Enter Employee officeId");
        Integer officeId = scanner.nextInt();
        orders.setProductId(officeId);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(orders);
        session.getTransaction().commit();
        session.close();
    }

    //for deleting a order , user will provide id
    public void removeOrder(int id, SessionFactory sessionFactory) {
        Orders order = new Orders();
        order.setOrderId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(order);
        session.getTransaction().commit();
        session.close();
    }

    //ToCheck stock of the products  , stock low if products is less than 10
    public void lowStock(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Products> criteria = builder.createQuery(Products.class);
        criteria.from(Products.class);
        List<Products> entityList = session.createQuery(criteria).getResultList();
        for (Products e : entityList) {
            if (e.getStockAmount() < 10) {
                System.out.println("Product " + e.getProductDescription() + " is low in stock");
            }
        }
        session.close();

    }


}
