package com.swing.rememberme.engine;


import java.awt.Point;

import com.swing.rememberme.engine.card.BasicCard;
import com.swing.rememberme.engine.user.User;
import com.swing.rememberme.engine.user.UserManager;

public class GameEngine {
	public static GameEngine engine = new GameEngine();
	public static GameEngine getInstance() {
		return engine;
	}
	
	private String level;
	private String category;
	int playtime, score;
	boolean bStarted;
	int livingCard;
	
	private String userName;
	
	public UserManager users = new UserManager();
	public GameBoard gameboard = new GameBoard();
	

	Point prevPos;
	
	public GameEngine() {
		users.loadFromFile("user.dat");
	}
	
	public void setLevel(String rhs){
		level=rhs;
	}
	
	public void setCategory(String rhs) {
		category=rhs;
	}
	
	
	public boolean ExistUser(String name, String pwd) {
		// TODO Auto-generated method stub
		for(User user : users.getUsers()) {
			if(name.equals(user.name) && pwd.equals(user.password))
				return true;
		}
		return false;
	}
	
	public boolean ExistName(String name) {
		// TODO Auto-generated method stub
		for(User user : users.getUsers()) {
			if(name.equals(user.name))
				return true;
		}
		return false;
	}
	
	int getPlayTime() {
		if(level == "Easy")
			return 100;
		if(level == "Medium")
			return 75;
		if(level == "High")
			return 50;
		return 0;
	}
	
	public int startGame() {
		playtime = getPlayTime();
		gameboard.boardinit(level, category);
		prevPos = new Point(-1, -1);
		bStarted = true;
		livingCard = 20;
		return playtime;
	}
	public void endGame() {
		bStarted = false;
		users.addScore(userName, level, category, score);
	}
	
	public void addScore(int x) {
		score+=x;
		if(score < 0)
			score = 0;
	}
	public int getScore() {
		return score;
	}
	
	public int tick() {
		if(livingCard <= 0)
			endGame();
		if(bStarted == false)
			return playtime;
		playtime--;
		if(playtime<=0)
			endGame();
		return playtime;
	}

	public BasicCard[][] getBoard() {
		// TODO Auto-generated method stub
		return gameboard.cards;
	}
	public void getNewOldname(String oldname, String newname,String oldpassword, String newpassword) {
		users.namepasswordEdit(newname, newpassword, oldname, oldpassword);
		
	}
	public Point clicked(int bx, int by) {
		if(bStarted == false)
			return new Point(-1, -1);
		Point ret = prevPos;
		
		if(prevPos.x != -1 && prevPos.y != -1)
		{
			if(gameboard.match(bx, by, prevPos.x, prevPos.y))
			{
				gameboard.discover(bx, by);
				gameboard.discover(prevPos.x, prevPos.y);
				addScore(10);
				livingCard --;
			}
			else
			{
				addScore(-1);
				livingCard ++;
			}
			prevPos = new Point(-1, -1);
		}
		else
		{
			prevPos = new Point(bx, by);
			livingCard --;
		}
		return ret;
				
	}
	
	public boolean isPlaying() {
		return bStarted;
	}

	public void setUser(String text) {
		// TODO Auto-generated method stub
		userName = text;
	}
}

