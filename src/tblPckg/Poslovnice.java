package tblPckg;

import java.io.Serializable;

public class Poslovnice implements Serializable {
	
	private static int cnt=0;
	private int id;
	private String name;
	private String contact;
	private String address;
	private String type;
	
	public Poslovnice() {
		
	}
	
	public Poslovnice(String name,String contact,String address,String type) {
		this.id=cnt;
		this.name=name;
		this.contact=contact;
		this.address=address;
		this.type=type;
		cnt++;
		
	}
	
	public Poslovnice(int id,String name,String contact,String address,String type) {
		this.id=cnt;
		this.name=name;
		this.contact=contact;
		this.address=address;
		this.type=type;
		cnt=id+1;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public String getAddress() {
		return address;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Poslovnice [id=" + id + ", name=" + name + ", contact=" + contact + ", address=" + address + ", type="
				+ type + "]";
	}
	
	public void description() {
		System.out.println(toString());
	}
	
	public static void setCounter(int i) {
		cnt=i;
	}

}
