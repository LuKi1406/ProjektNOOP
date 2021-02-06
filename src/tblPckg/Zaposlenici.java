package tblPckg;

import java.io.Serializable;

public class Zaposlenici implements Serializable {

	
	 private static int cnt=0;
	 private int id;
	 private String name;
	 private String surname;
	 private String address;
	 private String contact;
	 private String poslovnica;
	 private String datumPocetkaRada;
	 
	 public Zaposlenici() {
		 
		 
	 }
	 
	 public Zaposlenici(String name,String surname,String address,String contact,String poslovnica,String datumPocetkaRada) {
		 
		 this.id=cnt;
		 this.name=name;
		 this.surname=surname;
		 this.address=address;
		 this.contact=contact;
		 this.poslovnica=poslovnica;
		 this.datumPocetkaRada=datumPocetkaRada;
		 cnt++;
		 
	 }
	 
	 public Zaposlenici(int id,String name,String surname,String address,String contact,String poslovnica,String datumPocetkaRada) {
		 
		 this.id=id;
		 this.name=name;
		 this.surname=surname;
		 this.address=address;
		 this.contact=contact;
		 this.poslovnica=poslovnica;
		 this.datumPocetkaRada=datumPocetkaRada;
		 cnt=id+1;
		 
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

	public String getPoslovnica() {
		return poslovnica;
	}

	public String getDatumPocetkaRada() {
		return datumPocetkaRada;
	}

	@Override
	public String toString() {
		return "Zaposlenici [id=" + id + ", name=" + name + ", surname=" + surname + ", address=" + address
				+ ", contact=" + contact + ", poslovnica=" + poslovnica + ", datumPocetkaRada=" + datumPocetkaRada
				+ "]";
	}
	 
	 public void description() {
		 System.out.println(toString());
	 }
	 
	 
	 public static void setCounter(int i) {
		 cnt=i;
	 }

}
