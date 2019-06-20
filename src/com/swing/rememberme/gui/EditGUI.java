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
import javax.swing.SwingConstants;

import com.swing.rememberme.engine.GameEngine;
import com.swing.rememberme.engine.user.UserManager;



public class EditGUI extends JDialog {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1509059129827822688L;

	JLabel oldnameLabel = new JLabel("   Previous Name : ",SwingConstants.RIGHT);

	JLabel oldpasswordLabel = new JLabel("Previous Password : ",SwingConstants.RIGHT);
	JLabel newnameLabel = new JLabel("New Name : ",SwingConstants.RIGHT);
	JLabel newpasswordLabel = new JLabel("New Password : ",SwingConstants.RIGHT);
	JLabel confirmpasswordLabel = new JLabel("Confirm Password : ",SwingConstants.RIGHT);

	JTextField oldnameField = new JTextField();
	JPasswordField oldpasswordField = new JPasswordField();
	JTextField newnameField = new JTextField();
	JPasswordField newpasswordField = new JPasswordField();
	JPasswordField confirmpasswordField = new JPasswordField();
	JButton okButton = new JButton("OK");
	JButton updateButton = new JButton("Update");
	
	public UserManager users = new UserManager();	
	public EditGUI() {
		
		setupUI();
		setModal(true);
		setSize(400, 250);
		
		setUpListeners();
		

	}
	@SuppressWarnings("deprecation")
	private void CheckLogin() {
		if(GameEngine.getInstance().ExistUser(oldnameField.getText(), oldpasswordField.getText())) {
			// goto MainGUI
			oldnameField.setEditable(false);
			oldpasswordField.setEditable(false);
			newnameField.setEditable(true);
			newpasswordField.setEditable(true);
			confirmpasswordField.setEditable(true);
			okButton.setEnabled(false);
			
		} else {
			// Not Found
		    JOptionPane.showMessageDialog(null, "Please try again.");

		}
	}

	public void setupUI() {

		this.setTitle("Edit");

		JPanel topPanel = new JPanel(new GridBagLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		buttonPanel.add(okButton);
		buttonPanel.add(updateButton);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(4, 4, 4, 4);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		topPanel.add(oldnameLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		topPanel.add(oldnameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		topPanel.add(oldpasswordLabel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1;
		topPanel.add(oldpasswordField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		topPanel.add(newnameLabel, gbc);
		

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		topPanel.add(newnameField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		topPanel.add(newpasswordLabel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 1;
		topPanel.add(newpasswordField, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0;
		topPanel.add(confirmpasswordLabel, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.weightx = 1;
		topPanel.add(confirmpasswordField, gbc);
		newnameField.setEditable(false);
		newpasswordField.setEditable(false);
		confirmpasswordField.setEditable(false);
		this.add(topPanel);

		this.add(buttonPanel, BorderLayout.SOUTH);

	}

	private void setUpListeners() {
		oldpasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CheckLogin();
				}
			}
		});
		confirmpasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					UpdateUser();
				}
			}
		});
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckLogin();
				
			}
		});

		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateUser();
			}
			
		});
		
	}
	

	@SuppressWarnings("deprecation")
	void UpdateUser() {
		if(GameEngine.getInstance().ExistName(newnameField.getText())) {
			
			JOptionPane.showMessageDialog(null, "Please try again.");
			
		} else {
			
			if(!newpasswordField.getText().equals(confirmpasswordField.getText())) {
				
				JOptionPane.showMessageDialog(null, "Please try password.");
			}else {
				GameEngine.getInstance().getNewOldname(oldnameField.getText(), newnameField.getText(),oldpasswordField.getText(),newpasswordField.getText());
				setVisible(false);
	
			}
		    
		}
	}
	
}