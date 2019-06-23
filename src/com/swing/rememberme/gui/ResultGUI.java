package com.swing.rememberme.gui;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.swing.rememberme.engine.GameEngine;
import com.swing.rememberme.engine.user.User;

public class ResultGUI extends JDialog  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6390710330965090532L;

	public ResultGUI() {
		
		setModal(true);
		setSize(400, 250);
		setTitle("Result");
		
		String[] level = new String[] { "Easy", "Medium", "Hard" };
		String[] categories = new String[] { "Math", "Animals", "Flags", "Cars" };
		
        String[] columns = new String[] {
                "Name", "Level", "Category", "Score"
        };
         
        //actual data for the table in a 2d array
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        
        ArrayList<User> users = GameEngine.getInstance().users.getUsers();
        for(User user : users) {
        	for(int i = 0; i < 3; i ++)
        		for(int j = 0; j < 4; j ++) {
        			if(user.scores[i][j] > 0) {
        				Object[] item = new Object[] {user.name, level[i], categories[j], user.scores[i][j]};
        				data.add(item);
        			}
        		}
        }
        

        Object[][] itemsArray = new Object[data.size()][];
        itemsArray = data.toArray(itemsArray);
        //create table with data
        JTable table = new JTable(itemsArray, columns);
         
        //add the table to the frame
        this.add(new JScrollPane(table));
		
		setVisible(true);
	}
}
