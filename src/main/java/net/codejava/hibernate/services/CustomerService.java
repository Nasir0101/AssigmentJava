package net.codejava.hibernate.services;

import net.codejava.hibernate.DbModels.Customers;
import net.codejava.hibernate.DbModels.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
