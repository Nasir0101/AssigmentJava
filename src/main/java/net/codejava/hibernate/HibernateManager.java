package net.codejava.hibernate;

import net.codejava.hibernate.DbModels.*;
import net.codejava.hibernate.services.CustomerService;
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


    public static void main(String[] args) {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        CustomerService customerService = new CustomerService();
        customerService.getCustomers(sessionFactory);
    }

}
