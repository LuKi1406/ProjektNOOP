package tblPckg;

import java.io.Serializable;

public class Kupci implements Serializable {
	
	private static int cnt =0;
	private int id;
	private String name;
	private String surname;
	private String address;
	private String contact;
	private String numCard;
	
	public Kupci(String name,String surname,String address,String contact,String numCard) {
		this.id=cnt;
		this.name=name;
		this.surname=surname;
		this.address=address;
		this.contact=contact;
		this.numCard=numCard;
		cnt++;
		
	}
	
	public Kupci(int id,String name,String surname,String address,String contact,String numCard) {
		this.id=id;
		this.name=name;
		this.surname=surname;
		this.address=address;
		this.contact=contact;
		this.numCard=numCard;
		cnt=id+1;
	}

	public Kupci() {
		
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getContact() {
		
		return contact;
	}

	public String getNumCard() {
		return numCard;
	}

	@Override
	public String toString() {
		return "Kupci [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address + ", contact="
				+ contact + ", numCard=" + numCard + "]";
	}
	
	public void description() {
		System.out.println(toString());
	}

	
	
	public static void setCounter(int i) {
		cnt = i;
		
	}

}
