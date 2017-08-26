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

        ClientDAOImpl clientTable = new ClientDAOImpl();
        clientTable.initializeFactory();

      /* Add few employee records in database */
        int empID1 = clientTable.addClient("Zara", "Dublin", "D1");
        int empID2 = clientTable.addClient("Daisy", "Bray", "Castle");
        int empID3 = clientTable.addClient("John", "Blackrock", "Main");

      /* List down all the employees */
        clientTable.listClients();

      /* Update employee's records */
        clientTable.updateClient(empID1, "Andy");

      /* Delete an employee from the database */
        clientTable.deleteClient(empID2);

      /* List down new list of the employees */
        clientTable.listClients();
    }

}
