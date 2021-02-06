import java.util.EventObject;

import tblPckg.Kupci;

public class DataPanelEvent extends EventObject {
	
	private int id;
	private String name;
	private String surname;
	private String address;
	private String contact;
	private String numCard;
	
	
	public DataPanelEvent(Object source,Kupci kupac) {
		super(source);
		
		id=kupac.getId();
		name=kupac.getName();
		surname=kupac.getSurname();
		address=kupac.getAddress();
		contact=kupac.getContact();
		numCard=kupac.getNumCard();
		
		
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
	
	

	
	

}
