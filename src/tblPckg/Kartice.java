package tblPckg;

import java.io.Serializable;

public class Kartice implements Serializable {
	
	private static int cnt=0;
	private int id;
	private String cardNumber;
	private String cardType;
	private String cardPoints;
	
	
	public Kartice(String cardNumber,String cardType,String cardPoints) {
		this.id=cnt;
		this.cardNumber=cardNumber;
		this.cardType=cardType;
		this.cardPoints=cardPoints;
		cnt++;
		
	}
	
	public Kartice(int id,String cardNumber,String cardType,String cardPoints) {
		
		this.id=id;
		this.cardNumber=cardNumber;
		this.cardType=cardType;
		this.cardPoints=cardPoints;
		cnt=id+1;
	}
	
	public Kartice() {
		
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

	@Override
	public String toString() {
		return "Kartice [id=" + id + ", cardNumber=" + cardNumber + ", cardType=" + cardType + ", cardPoints="
				+ cardPoints + "]";
	}
	
	public void description() {
		System.out.println(toString());
	}
	
	public static void setCounter(int i) {
		cnt=i;
	}

}
