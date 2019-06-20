package com.swing.rememberme.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.swing.rememberme.engine.card.BasicCard;

public class MyButton extends JButton {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2932992784085000845L;
	
	
	BasicCard mycards;
	boolean bFlipped;
	public int row, col;
	
	public MyButton(BasicCard cards, int row, int col){
		this.bFlipped = false;
		this.mycards = cards;
		this.row = row;
		this.col = col;
	}
	
	public BasicCard getCard() {
		return mycards;
	}
	
	public void Flip(){
		if(!mycards.getDiscovered()) {
			if(bFlipped==true) {
				setText("");
				setIcon(null);
			}
			else {
				switch(mycards.getCardType())
				{
				case NAME_CARD: case PROBLEM_CARD: case SOLUTION_CARD:
					setText(mycards.getCardContent());
					break;
				default:
					ImageIcon icon = new ImageIcon(mycards.getCardContent());
					Image im = icon.getImage();
					Image im2 = im.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
					setIcon(new ImageIcon(im2));
				}
				
			}
			bFlipped = !bFlipped;
		}
	}
	
	public boolean getFlipped() {
		return bFlipped;
	}

}
