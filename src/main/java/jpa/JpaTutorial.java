package jpa;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JpaTutorial {
    private static SessionFactory factory;
    public static void main(String[] args) {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        JpaTutorial tutorial = new JpaTutorial();

      /* Add few employee records in database */
        int empID1 = tutorial.addClient("Zara", "Dublin", "D1");
        int empID2 = tutorial.addClient("Daisy", "Bray", "Castle");
        int empID3 = tutorial.addClient("John", "Blackrock", "Main");

      /* List down all the employees */
        tutorial.listClients();

      /* Update employee's records */
        tutorial.updateClient(empID1, "Andy");

      /* Delete an employee from the database */
        tutorial.deleteClient(empID2);

      /* List down new list of the employees */
        tutorial.listClients();
    }

    /* Method to CREATE an employee in the database */
    public int addClient(String name, String address1, String address2){
        Session session = factory.openSession();
        Transaction tx = null;
        int clientID = 0;
        try{
            tx = session.beginTransaction();
            Client client = new Client(name, address1, address2);
            clientID = (int)((Integer) session.save(client));
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return clientID;
    }

    /* Method to  READ all the employees */
    public void listClients( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM jpa.Client").list();
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext();){
                Client client = (Client) iterator.next();
                System.out.print("Name: " + client.getName());
                System.out.print(" Address1: " + client.getAddress1());
                System.out.println(" Address2: " + client.getAddress2());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /* Method to UPDATE salary for an employee */
    public void updateClient(int employeeId, String name ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Client client = (Client)session.get(Client.class, employeeId);
            client.setName( name );
            session.update(employeeId);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteClient(int employeeId){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Client client = (Client)session.get(Client.class, employeeId);
            session.delete(client);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
