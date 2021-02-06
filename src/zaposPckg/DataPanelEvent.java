package zaposPckg;

import java.util.EventObject;

import tblPckg.Zaposlenici;

public class DataPanelEvent extends EventObject {
    
	private int id;
    private String name;
    private String surname;
    private String address;
    private String contact;
    private String poslovnica;
    private String datumPocetkaRada;
    
	public DataPanelEvent(Object source,Zaposlenici zaposlenik) {
		super(source);
		
		id=zaposlenik.getId();
		name=zaposlenik.getName();
		surname=zaposlenik.getSurname();
		address=zaposlenik.getAddress();
		contact=zaposlenik.getContact();
		poslovnica=zaposlenik.getPoslovnica();
		datumPocetkaRada=zaposlenik.getDatumPocetkaRada();
		
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
	
	

}
