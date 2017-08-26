package jpa;

import java.util.List;

public abstract class ClientDAO {

    public abstract void listClients();
    public abstract int addClient(String name, String address1, String address2);
    public abstract void updateClient(int employeeId, String name );
    public abstract void deleteClient(int employeeId);


}
