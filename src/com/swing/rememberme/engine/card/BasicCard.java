package com.swing.rememberme.engine.card;

public abstract class BasicCard {
	int cardID;
	public int getCardID() {
		return cardID;
	}
	public void setCardID(int rhs) {
		cardID = rhs;
	}
	
	String cardContent;
	public String getCardContent() {
		return cardContent; 
	}
	public void setCardContent(String rhs) {
		cardContent = rhs;
	}
	
	boolean bDiscovered = false;
	public boolean getDiscovered() {
		return bDiscovered;
	}
	public void setDiscovered(boolean rhs) {
		bDiscovered = rhs;
	}
	
	CardType cardType;
	public CardType getCardType() {
		return cardType;
	}
	public void setCardType(CardType rhs) {
		cardType = rhs;
	}

	public enum CardType {
		CAR_CARD,			// for car logos
		FLAG_CARD,			// for flag images
		ANIMAL_CARD,		// for animal images
		NAME_CARD,			// for name of company, country & animal
		PROBLEM_CARD,		// for math problem
		SOLUTION_CARD		// for math solution
	}
}
