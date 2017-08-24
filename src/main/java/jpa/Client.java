package jpa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Client {

	@Id
    private int id;
	private String name;
	private String address1;
	private String address2;

	public Client() {
	}

	public Client(String name, String address1, String address2) {
		this.name = name;
		this.address1 = address1;
		this.address2 = address2;
	}

    public int getId() {
        return id;
    }

    public void setId(int eid) {
        this.id = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String ename) {
        this.name = ename;
    }

	public String getAddress1() { return address1;	}

	public void setAddress1(String address1) { this.address1 = address1; }

	public String getAddress2() { return address2;	}

	public void setAddress2(String address2) {	this.address2 = address2; }
}
