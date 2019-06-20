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
;

/**
 * A Simple Login Dialog
 * 
 * @author Oliver Watkins (c)
 */
public class LoginGUI extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel nameLabel = new JLabel("Name : ");
	JLabel passwordLabel = new JLabel("Password : ");

	JTextField nameField = new JTextField();
	JPasswordField passwordField = new JPasswordField();

	JButton loginButton = new JButton("Log In");
	JButton signupButton = new JButton("Sign Up");
	

	public LoginGUI() {
		
		setupUI();
		
		setModal(true);
		setSize(400, 250);
		setUpListeners();

	}

	public void setupUI() {

		this.setTitle("Login");

		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.add(loginButton);
		buttonPanel.add(signupButton);

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

		this.add(topPanel);

		this.add(buttonPanel, BorderLayout.SOUTH);

	}
	
	@SuppressWarnings("deprecation")
	private void CheckLogin() {
		if(GameEngine.getInstance().ExistUser(nameField.getText(), passwordField.getText())) {
			// goto MainGUI
			GameEngine.getInstance().setUser(nameField.getText());
			setVisible(false);
		} else {
			// Not Found
		    JOptionPane.showMessageDialog(null, "Please try again.");

		}
	}

	private void setUpListeners() {
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CheckLogin();
				}
			}
		});

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckLogin();
			}
		});

		signupButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				LoginDialog.this.setVisible(false);
				SignupGUI signgui=new SignupGUI();
				signgui.setVisible(true);
				
			}
		});
	}
	
}