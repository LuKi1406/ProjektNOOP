package karticePckg;

import java.util.EventObject;

import tblPckg.Kartice;

public class DataPanelEvent extends EventObject {
	private int id;
	private String cardNumber;
	private String cardType;
	private String cardPoints;
	

	public DataPanelEvent(Object source,Kartice kartica) {
		super(source);
		this.id=kartica.getId();
		this.cardNumber=kartica.getCardNumber();
		this.cardType=kartica.getCardType();
		this.cardPoints=kartica.getCardPoints();
	}


	public int getId() {
		return id;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public String getCardType() {
		return cardType;
	}


	public String getCardPoints() {
		return cardPoints;
	}
	
	
	

}
