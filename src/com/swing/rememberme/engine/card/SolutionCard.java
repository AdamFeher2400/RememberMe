package com.swing.rememberme.engine.card;

public class SolutionCard extends StringCard{
	
	public SolutionCard(int refID, String content) {
		
		setCardType(CardType.SOLUTION_CARD);
		setCardID(refID);
		setCardContent(content);
	}
	
}



