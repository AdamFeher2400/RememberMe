package com.swing.rememberme.engine;

import java.awt.Point;
import java.util.Random;
import com.swing.rememberme.engine.card.AnimalsCard;
import com.swing.rememberme.engine.card.BasicCard;
import com.swing.rememberme.engine.card.CarsCard;
import com.swing.rememberme.engine.card.FlagsCard;
import com.swing.rememberme.engine.card.NameCard;
import com.swing.rememberme.engine.card.ProblemCard;
import com.swing.rememberme.engine.card.SolutionCard;

public class GameBoard {
	BasicCard[][] cards = new BasicCard[4][5];
	
	
	String[][] cars = {
			{
				"audi", "bmw", "Chevrolet", "ferrari", "hyundai", "marchedrs", "Nissan", "renault", "subaru", "toyota"
			},
			{
				"alfa_romeo","bentley","Ford","honda","Jaguar","mustang","skoda","Suzuki","Volkswagen","Volvo"
			},
			{
				"Aston Martin","buick","cadillac","Fiat","Infiniti","Lamborghini","Maybach","Mitsubushi","perodua","Peugeot"
			}
	};
	String[][] animals = {
			{
				"butterfly", "cat", "dog", "dolphin", "elephant", "fish", "lion", "tiger", "turtle", "zebra"
			},
			{
				"ants","beige pig","chameleon","deer","flamingo","fox","grizzly bear","kolala bear","lama","ram"
			},
			{
				"Angel-Shark","Bat","Durrelli","Hirola","Luristan-Newt","macro","Rare-Saola","Sloth","spoon-bille","Wombat"
			}
	};
	String[][] flags = {
			{
				"Argentina", "Australia", "Brazil", "Canada", "Germany", "Israel", "Spain", "Turkey", "United Kingdom", "United States"
			},
			{
				"China","France","Greece","Japan","Mexico","Morocco","Netherlands","Portugal","Sweden","Switzerland"
			},
			{
				"Côte d'Ivoire","Czech Republic","Georgia","India","Peru","Philippines","Poland","Thailand","Yemen","Zambia"
			}
	};
	
	String[][] problems = {
			{
				"1+1", "1+2", "1+3", "2+3", "3+3", "3+4", "4+4", "5+4", "4+6","3+8"
			},
			{
				"12+24", "17+12", "21+3", "22+3", "13+13", "13+14", "14+14", "15+14", "14+16","13+18"
			},
			{
				"5*6", "9*4", "9*3", "7*8", "6*5", "12*6", "11*9", "7*10", "4*15","6*7"
			}
	};
	
	String[][] solutions = {
			{
				"2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
			},
			{
				"36","29","24","25","26","27","28","29","30","31"
			},
			{
				"30","36","27","56","30","72","99","70","60","42"
			}
	};

		
	public void boardinit(String level, String category) {
		for(int i = 0; i < 4; i ++)
			for(int j = 0; j < 5; j ++)
				cards[i][j] = null;
		
		int index = 0;
		if(level == "Easy")
			index = 0;
		if(level == "Medium")
			index = 1;
		if(level == "High")
			index = 2;
		
		String[] left = null, right = null;
		if(category == "Math")
		{
			left = problems[index];
			right = solutions[index];
		}
		
		else
		{
			if(category == "Cars")
				left = cars[index];
			if(category == "Flags")
				left = flags[index];
			if(category == "Animals")
				left = animals[index];
			
			right = new String[10];
			
			for(int i = 0; i < 10; i ++)
			{
				right[i] = "pic/" + category + "/level" + (index+1) + "/" + left[i] + ".png";
			}
		}
		
		for(int i = 0; i < 10; i ++)
		{
			Point pos = getRandomPosition();
			cards[pos.x][pos.y] = generateCard(category, i, left[i], 0);
			pos = getRandomPosition();
			cards[pos.x][pos.y] = generateCard(category, i, right[i], 1);
		}
	}


	private BasicCard generateCard(String category, int refID, String content, int isRight) {
		// TODO Auto-generated method stub
		
		if(isRight == 0) {
			if(category == "Math")
				return new SolutionCard(refID, content);
			if(category == "Animals")
				return new NameCard(refID, content);
			if(category == "Cars")
				return new NameCard(refID, content);
			if(category == "Flags")
				return new NameCard(refID, content);
		}
		
		if(isRight == 1) {
			if(category == "Math")
				return new ProblemCard(refID, content);
			if(category == "Animals")
				return new AnimalsCard(refID, content);
			if(category == "Cars")
				return new CarsCard(refID, content);
			if(category == "Flags")
				return new FlagsCard(refID, content);
		}
		return null;
	}

	private Point getRandomPosition() {
		// TODO Auto-generated method stub
		int x = -1, y = -1;
		Random rand = new Random();
		do {
			int z = rand.nextInt(20);
			x = z / 5;
			y = z % 5;
		} while(cards[x][y] != null);
		
		return new Point(x, y);
	}


	public boolean match(int bx, int by, int x, int y) {
		// TODO Auto-generated method stub
		return cards[bx][by].getCardID() == cards[x][y].getCardID();
	}


	public void discover(int bx, int by) {
		// TODO Auto-generated method stub
		cards[bx][by].setDiscovered(true);
	}
	
}