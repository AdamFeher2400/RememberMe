package com.swing.rememberme.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.swing.rememberme.engine.GameEngine;

//import memory.engine.DataManagement;


public class SignupGUI extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -245814338128440933L;

	JLabel nameLabel = new JLabel("Name : ");
	JLabel passwordLabel = new JLabel("Password : ");
	JLabel confirmLabel = new JLabel("Confirm : ");

	JTextField nameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();
	JPasswordField confirmField = new JPasswordField();

	JButton okButton = new JButton("OK");
	JButton cancelButton = new JButton("Cancel");
	
	
	public SignupGUI() {
		setupUI();
		this.setModal(true);
		this.setSize(400, 250);
		//new DataManagement();
		setUpListeners();
		
	}

	public void setupUI() {

		this.setTitle("Register");

		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4, 4, 4, 4);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		topPanel.add(nameLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		topPanel.add(nameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		topPanel.add(passwordLabel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		topPanel.add(passwordField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		topPanel.add(confirmLabel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 1;
		topPanel.add(confirmField, gbc);

		this.add(topPanel);

		this.add(buttonPanel, BorderLayout.SOUTH);

	}
	
	@SuppressWarnings("deprecation")
	private void CheckSingnup() {
		if(GameEngine.getInstance().ExistName(nameField.getText())) {
			
			JOptionPane.showMessageDialog(null, "Name duplicated");
			
		} else {
			
			if(!passwordField.getText().equals(confirmField.getText())) {
				
				JOptionPane.showMessageDialog(null, "Invalid Password.");
			}else {
				GameEngine.getInstance().users.addUser(nameField.getText(), passwordField.getText());
				GameEngine.getInstance().users.saveToFile("user.dat");
				this.setVisible(false);
			}
		    
		}
	}

	private void setUpListeners() {

		confirmField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					//Check duplicated name
					CheckSingnup();			
				}
			}
		});

		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckSingnup();
				
				
			}
		});

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				LoginDialog.this.setVisible(false);
				setVisible(false);
				

			}
		});
	}
	
}