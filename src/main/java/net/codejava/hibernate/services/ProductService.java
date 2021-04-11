package net.codejava.hibernate.services;

import net.codejava.hibernate.DbModels.Customers;
import net.codejava.hibernate.DbModels.Orders;
import net.codejava.hibernate.DbModels.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.*;

public class ProductService {
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

    //for deleting a customer , user will provide id
    public void removeCustomer(int id, SessionFactory sessionFactory) {
        Products product = new Products();
        product.setProductId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(product);
        session.getTransaction().commit();
        session.close();
    }

    //Getting all customers from the database
    public void getProducts(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Products> criteria = builder.createQuery(Products.class);
        criteria.from(Products.class);
        List<Products> entityList = session.createQuery(criteria).getResultList();
        for (Products e : entityList) {
            System.out.print("  Product Name: " + e.getProductName()+ "  Product Id : " + e.getProductId() + "  Stock Amount : " + e.getStockAmount() + "  WarehouseId : : " + e.getWarehouseId() + " Product Price : " + e.getProductPrice());
        }
        session.close();
    }






}
