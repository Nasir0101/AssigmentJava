package net.codejava.hibernate.services;

import net.codejava.hibernate.DbModels.Offices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Scanner;

public class OfficeService {

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



}
