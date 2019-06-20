package com.swing.rememberme.engine.card;

public abstract class PictureCard extends BasicCard {
	
	public String getPath() {
		return getCardContent();
	}
	public void setPath(String path) {
		setCardContent(path);
	}

}
