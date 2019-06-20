package com.swing.rememberme.engine.card;


public class NameCard extends StringCard{
	
	public NameCard(int refID, String content) {
		
		setCardType(CardType.NAME_CARD);
		setCardID(refID);
		setCardContent(content);
	}
}
