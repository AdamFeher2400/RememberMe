package com.swing.rememberme.engine.card;

public class AnimalsCard extends PictureCard {

	public AnimalsCard(int refID, String path) {
		setCardType(CardType.ANIMAL_CARD);
		setCardID(refID);
		setPath(path);
	}
}
