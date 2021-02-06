package poslovnicePckg;

import java.util.EventObject;

import tblPckg.Poslovnice;

public class DataPanelEvent extends EventObject {
	
    private int id;
    private String name;
    private String contact;
    private String address;
    private String type;
    
	public DataPanelEvent(Object source,Poslovnice poslovnica) {
		super(source);
		this.id=poslovnica.getId();
		this.name=poslovnica.getName();
		this.contact=poslovnica.getContact();
		this.address=poslovnica.getAddress();
		this.type=poslovnica.getType();
		
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
	
	
	

	
}
