package jpa;

public class Person {

	private int id;
	private String name;

	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Person() {
	}

	public int getEid() {
		return id;
	}

	public void setEid(int eid) {
		this.id = eid;
	}

	public String getEname() {
		return name;
	}

	public void setEname(String ename) {
		this.name = ename;
	}

}