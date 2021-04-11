package net.codejava.hibernate.services;

import net.codejava.hibernate.DbModels.Customers;
import net.codejava.hibernate.DbModels.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class CustomerService {

    //Getting all customers from the database
    public void getCustomers(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Customers> criteria = builder.createQuery(Customers.class);
        criteria.from(Customers.class);
        List<Customers> entityList = session.createQuery(criteria).getResultList();
        for (Customers e : entityList) {
            System.out.print("  Customer Id : " + e.getCustomerId() + "  FirstName : " + e.getFirstName() + "  LastName : " + e.getLastName() + "  Email : : " + e.getEmail() + "  Customer Comment : " + e.getCustomerComment());
        }
        session.close();
    }


    //for deleting a customer , user will provide id
    public void removeCustomer(int id, SessionFactory sessionFactory) {
        Customers customer = new Customers();
        customer.setCustomerId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(customer);
        session.getTransaction().commit();
        session.close();
    }

    //Create a new Product, user provide all the fields
    protected void createCustomer(SessionFactory sessionFactory) {
        Customers customer = new Customers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer id");
        int id = scanner.nextInt();
        customer.setCustomerId(id);
        System.out.println("Enter customer  firstName");
        String name = scanner.next();
        customer.setFirstName(name);
        System.out.println("Enter customer lastName");
        String description = scanner.next();
        customer.setLastName(description);
        System.out.println("Enter customer email");
        String email = scanner.next();
        customer.setEmail(email);
        System.out.println("Enter customer address");
        String address = scanner.next();
        customer.setAddress(address);
        System.out.println("Enter organization Id");
        Integer organization = scanner.nextInt();
        customer.setOrganisationId(organization);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    //for updating a product , user will provide id
    protected void updateProducts(int id, SessionFactory sessionFactory) {
        Customers customer = new Customers();
        Scanner scanner = new Scanner(System.in);
        customer.setCustomerId(id);
        System.out.println("Enter customer  firstName");
        String name = scanner.next();
        customer.setFirstName(name);
        System.out.println("Enter customer lastName");
        String description = scanner.next();
        customer.setLastName(description);
        System.out.println("Enter customer email");
        String email = scanner.next();
        customer.setEmail(email);
        System.out.println("Enter customer address");
        String address = scanner.next();
        customer.setAddress(address);
        System.out.println("Enter organization Id");
        Integer organization = scanner.nextInt();
        customer.setOrganisationId(organization);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(customer);
        session.getTransaction().commit();
        session.close();
    }

    //To find customer with most orders
    public void customerWithMostOrders(SessionFactory sessionFactory) {
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


}
