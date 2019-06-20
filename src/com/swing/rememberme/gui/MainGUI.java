package com.swing.rememberme.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import com.swing.rememberme.engine.GameEngine;
import com.swing.rememberme.engine.card.BasicCard;

public class MainGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8748601621575522714L;
	
	// SignIn, SignUp, Menu (Level, Category), UserEdit, UserData, History
	// MainGUI (Board, Start, Score, Time:ProgressBar)
	//LoginGUI logingui=new LoginG
	
	JPanel jpanel;
	LoginGUI login;
	EditGUI editgui;
	
	JButton startbutton;
	JLabel score;
	JProgressBar timeprogress;
	
	GameEngine engine = GameEngine.getInstance();
	
	public String levelcheck;
	public String categorycheck;
	public int playtime;
	public int socre;
	

	Timer timer = new Timer("Timer"); 
	
	void createMenuBar() {
        var menuBar = new JMenuBar();
        
        var editMenuItem = new JMenuItem("Edit");
        var levelMenu = new JMenu("Level");
        var categoryMenu = new JMenu("Category");
        var dataMenu = new JMenu("Data");
        var easyMenuItem = new JRadioButtonMenuItem("Easy",true);
        var mediumMenuItem = new JRadioButtonMenuItem("Medium");
        var highMenuItem = new JRadioButtonMenuItem("High");
        var mathMenuItem = new JRadioButtonMenuItem("Math",true);
        var animalsMenuItem = new JRadioButtonMenuItem("Animals");
        var flagsMenuItem = new JRadioButtonMenuItem("Flags");
        var carsMenuItem = new JRadioButtonMenuItem("Cars");
        var scoreBoardMenuItem = new JMenuItem("Score Board");
        ButtonGroup levelgroup=new ButtonGroup();
        ButtonGroup categorygroup = new ButtonGroup();
        
        menuBar.add(levelMenu);
        levelMenu.add(easyMenuItem);
        levelMenu.add(mediumMenuItem);
        levelMenu.add(highMenuItem);
        levelgroup.add(easyMenuItem);
        levelgroup.add(mediumMenuItem);
        levelgroup.add(highMenuItem);
        
        menuBar.add(categoryMenu);
        categoryMenu.add(mathMenuItem);
        categoryMenu.add(animalsMenuItem);
        categoryMenu.add(flagsMenuItem);
        categoryMenu.add(carsMenuItem);
        
        categorygroup.add(mathMenuItem);
        categorygroup.add(animalsMenuItem);
        categorygroup.add(flagsMenuItem);
        categorygroup.add(carsMenuItem);

        menuBar.add(dataMenu);
        dataMenu.add(scoreBoardMenuItem);
        dataMenu.add(editMenuItem);
        

        this.setJMenuBar(menuBar);
        
        editMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
        		editgui = new EditGUI();

            	editgui.setVisible(true);
            }
        });
        
       
        scoreBoardMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	
            	///////////////////////////////////
            	new ResultGUI();
            }
        });
        
		
        easyMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setLevel("Easy");
            	
            }
        });
        mediumMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setLevel("Medium");
            }
        });
        highMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setLevel("High");
            }
        });
        mathMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setCategory("Math");
            }
        });
        animalsMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setCategory("Animals");
            }
        });
        flagsMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setCategory("Flags");
            }
        });
        carsMenuItem.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	engine.setCategory("Cars");
            }
        });
        
        easyMenuItem.doClick();
        mathMenuItem.doClick();
	}
	

	MainGUI() {
		// Start Login
		
		login = new LoginGUI();

	    
	    login.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);



		login.setVisible(true);
  
		createMenuBar();
		
       
        this.setTitle("MemoryGame");
        this.setSize(535, 562);
		this.setResizable(false);
		
        jpanel=new JPanel(); 
        jpanel.setBounds(10,10,500, 400);
        jpanel.setBackground(Color.GRAY);
        GridLayout grid = new GridLayout(4, 5);
        grid.setHgap(1);
        grid.setVgap(1);
        jpanel.setLayout(grid);
        
        this.add(jpanel);
		        
        timeprogress=new JProgressBar();
        startbutton=new JButton();
        startbutton.setText("Start");
        score=new JLabel();
        score.setText("Score");
        
        startbutton.setBounds(40, 450, 100, 30);
        score.setBounds(250, 450, 100, 30);
        timeprogress.setBounds(10, 420, 500, 20);
        this.add(startbutton);
        this.add(score);
        this.add(timeprogress);
        this.setLayout(null);
        this.setVisible(true);
        
        startbutton.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
            	jpanel.removeAll();
            	int playtime = engine.startGame();
            	timeprogress.setMaximum(playtime);
            	timeprogress.setValue(playtime);
            	score.setText("0");
            	
            	timer.cancel();
            	timer.purge();
            	timer = new Timer("Timer"); 
				timer.schedule(new TimerTask() {
					public void run() {
						timeprogress.setValue(engine.tick());
						if(timeprogress.getValue() * 1.0 / timeprogress.getMaximum() < 0.3)
						    timeprogress.setForeground(Color.RED);
						if(!GameEngine.getInstance().isPlaying())
						{
							JOptionPane.showMessageDialog(null, "Game ended");
							timer.cancel();
			            	timer.purge();
							new ResultGUI();
						}
					}
				}, 1000, 1000);
				
            	BasicCard[][] cards = engine.getBoard();
            	MyButton[][] buttons = new MyButton[4][5];
            	for(int i = 0; i < 4; i ++) {
            		for(int j = 0; j < 5; j ++) {
            			buttons[i][j] = new MyButton(cards[i][j], i, j);
            			jpanel.add(buttons[i][j]);
            	        buttons[i][j].addActionListener(new ActionListener(){
            	        	public void actionPerformed(ActionEvent e) {	
            	        		  if(!engine.isPlaying())
            	        			  return;
	    	        		      MyButton sender = (MyButton)e.getSource();
	    	        		      if(sender.getFlipped())
	    	        		    	  return;
	    	        		      sender.Flip();
	    	        		      
	    	        		      Point before = engine.clicked(sender.row, sender.col);
	    	        		      score.setText(engine.getScore() + "");
	    	        		      Timer timer = new Timer("Timer"); 
	    	        		      timer.schedule(new TimerTask() {
	    	        		          public void run() {
	    	    	        		      if(before.x != -1 && before.y != -1) {
	    	    	        		    	  if(buttons[before.x][before.y].getCard().getDiscovered()) {
	    	    	        		    		  Color color = new Color((new Random()).nextInt(0xFFFFFF));
	    	    	        		    		  sender.setBorder(new LineBorder(color));
	    	    	        		    		  buttons[before.x][before.y].setBorder(new LineBorder(color));
	    	    	        		    	  }
	    	    	        		    	  else {
		    	    	        		    	  buttons[before.x][before.y].Flip();
		    		    	        		      sender.Flip();
	    	    	        		    	  }
	    	    	        		      }
	    	        		          }
	    	        		      }, 250);
	    	        		}
            	        });
            		}
            	}

            	jpanel.revalidate();
            	jpanel.repaint();
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new MainGUI();
	}

}
