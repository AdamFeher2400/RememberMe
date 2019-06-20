package com.swing.rememberme.engine.card;

public class CarsCard extends PictureCard {
	public CarsCard(int refID, String path) {
		setCardType(CardType.CAR_CARD);
		setCardID(refID);
		setPath(path);
	}
}
