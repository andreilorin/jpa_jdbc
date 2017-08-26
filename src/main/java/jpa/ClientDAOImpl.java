package jpa;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ClientDAOImpl extends ClientDAO {

    private static SessionFactory factory;

    public void initializeFactory() {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

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
