package com.swing.rememberme.engine.card;

public class FlagsCard extends PictureCard{
	public FlagsCard(int refID, String path) {
		setCardType(CardType.FLAG_CARD);
		setCardID(refID);
		setPath(path);
	}
}
