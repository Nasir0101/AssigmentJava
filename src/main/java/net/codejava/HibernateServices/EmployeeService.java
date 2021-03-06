package net.codejava.HibernateServices;

import net.codejava.DbModels.Employees;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {


    //Create a new employeee, user provide all the fields
    public void createEmployee(SessionFactory sessionFactory) {
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

    //Getting all the employees from the database
    public void getEmployees(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Employees> criteria = builder.createQuery(Employees.class);
        criteria.from(Employees.class);
        List<Employees> entityList = session.createQuery(criteria).getResultList();
        for (Employees e : entityList) {
            System.out.print("  FirstName: " + e.getFirstName() + "  LastName: " + e.getLastName() + "  Email: " + e.getEmail() + "  Address: " + e.getAddress() + "  EmployeeComment: " + e.getEmployeeComment() + "  OfficeId: " + e.getOfficeId());
        }
        session.close();
    }

    //for updating a employee , user will provide id
    public void updateEmployee(int id, SessionFactory sessionFactory) {
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

    //for deleting a employee , user will provide id
    public void removeEmployee(int id, SessionFactory sessionFactory) {
        Employees employee = new Employees();
        employee.setEmployeeId(id);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }

}
