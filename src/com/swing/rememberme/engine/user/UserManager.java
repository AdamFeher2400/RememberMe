package com.swing.rememberme.engine.user;
import org.json.*;

import java.io.*;
import java.util.ArrayList;

public class UserManager {
	ArrayList<User> users;
	
	public UserManager() {
		users = new ArrayList<User>();
	}
	
	public void addUser(String name, String password) {
		User user = new User();
		user.name = name;
		user.password = password;
		users.add(user);
	}
	
	public void loadFromFile(String fn) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fn));
			String line = reader.readLine();
			String inputStr = "";
			while(line != null) {
				inputStr += line;
				line = reader.readLine();
			}
			reader.close();
			users.clear();
			JSONArray usersArray = new JSONArray(inputStr);
			for(int i = 0; i < usersArray.length(); i ++) {
				JSONObject userObject = usersArray.getJSONObject(i); 
				User user = new User();
				user.name = userObject.getString("name");
				user.password = userObject.getString("password");
				JSONArray scoreList = userObject.getJSONArray("score");
				for(int j = 0; j < scoreList.length() && j < 12; j ++)
					user.scores[j / 4][j % 4] = scoreList.getInt(j);
				users.add(user);
			}
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
	}
	
	public void saveToFile(String fn) { 
		try {
			FileWriter writer = new FileWriter(fn);
			JSONArray usersArray = new JSONArray();
			for(User user : users) {
				JSONObject userObject = new JSONObject();
				
				userObject.put("name", user.name);
				userObject.put("password", user.password);
				
				JSONArray scoreList = new JSONArray();
				for(int i = 0; i < 3; i ++)
					for(int j = 0; j < 4; j ++)
						scoreList.put(user.scores[i][j]);
				
				userObject.put("score", scoreList);
				usersArray.put(userObject);
			}
			writer.write(usersArray.toString());
			writer.close();
		} catch (Exception ex) { 
			ex.printStackTrace();
		}
	}
	
	public void namepasswordEdit(String newname, String newpassword,String oldname,String oldpassword) {
		for(User user : users) {
			if(user.name.equals(oldname) && user.password.equals(oldpassword)) {
				user.name = newname;
				user.password = newpassword;
			}
			saveToFile("user.dat");
		}
		
	}
	

	public ArrayList<User> getUsers() {
		// TODO Auto-generated method stub
		return users;
	}

	public void addScore(String userName, String level, String category, int score) {
		// TODO Auto-generated method stub
		for(User user : users) {
			if(user.name.equals(userName)) {
				int i = 0;
				if(level == "Easy")		i = 0;
				if(level == "Medium")	i = 1;
				if(level == "High")		i = 2;
				
				int j = 0;
				if(category == "Math")		j = 0;
				if(category == "Animals")	j = 1;
				if(category == "Flags")		j = 2;
				if(category == "Cars")		j = 3;
				
				user.scores[i][j] = score;
				saveToFile("user.dat");
				break;
			}
		}
	}

}
