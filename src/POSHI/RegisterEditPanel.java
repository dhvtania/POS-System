package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSHI.RegisterListPanel;
import POSPD.Register;
import POSPD.Store;

public class RegisterEditPanel extends JPanel {
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, boolean isAdd) {
		setLayout(null);
		
		JLabel lblEditRegister = new JLabel("Edit Register");
		lblEditRegister.setBounds(167, 24, 130, 16);
		add(lblEditRegister);
		
		JLabel lblRegisterNumeber = new JLabel("Register Numeber:");
		lblRegisterNumeber.setBounds(46, 80, 124, 16);
		add(lblRegisterNumeber);
		
		textField = new JTextField(register.getNumber());
		textField.setBounds(225, 75, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !register.getNumber().equals(textField.getText()))
				{
					store.removeRegister(register);
					register.setNumber(textField.getText());
					store.addRegister(register);;
				}
				if (isAdd) 
				{
					register.setNumber(textField.getText());
					store.addRegister(register);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(68, 202, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(238, 202, 117, 29);
		add(btnCancel);

	}

}
