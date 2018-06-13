package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSHI.CashierListPanel;
import POSPD.Cashier;
import POSPD.Person;
import POSPD.Store;
import javax.swing.JPasswordField;

public class CashieEditPanel extends JPanel {
	
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_1;
	private JPasswordField textField_5;

	/**
	 * Create the panel.
	 */
	public CashieEditPanel(JFrame currentFrame, Store store, Cashier cashier, boolean isAdd) {
		Person person;
		person = cashier.getPerson();
		setLayout(null);
		
		JLabel lblEditCashier = new JLabel("Edit Cashier");
		lblEditCashier.setBounds(191, 29, 118, 28);
		add(lblEditCashier);
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(16, 72, 61, 16);
		add(lblNumber);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(16, 102, 61, 16);
		add(lblName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(16, 130, 61, 16);
		add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(16, 158, 61, 16);
		add(lblCity);
		
		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(16, 186, 61, 16);
		add(lblPhone);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(16, 214, 73, 16);
		add(lblPassword);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !cashier.getNumber().equals(textField.getText()))
				{
					store.removeCashier(cashier);
					cashier.setNumber(textField.getText());
					store.addCashier(cashier);
				}
				if (isAdd) 
				{
					cashier.setNumber(textField.getText());
					store.addCashier(cashier);
				}
				
				
				person.setName(textField_1.getText());
				person.setAddress(textField_3.getText());
				person.setPhone(textField_4.getText());
				person.setSsn(textField_8.getText());
				person.setCity(textField_2.getText());
				person.setZip(textField_7.getText());
				person.setState(textField_6.getText());
				
			
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
			
		});
		btnSave.setBounds(90, 248, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(253, 248, 117, 29);
		add(btnCancel);
		
		JLabel lblSsn = new JLabel("SSN:");
		lblSsn.setBounds(219, 72, 61, 16);
		add(lblSsn);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(231, 130, 61, 16);
		add(lblState);
		
		JLabel lblZip = new JLabel("ZIP:");
		lblZip.setBounds(334, 130, 61, 16);
		add(lblZip);
		
		textField = new JTextField(cashier.getNumber());
		textField.setBounds(90, 69, 124, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField(person.getCity());
		textField_2.setBounds(90, 153, 130, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField(person.getAddress());
		textField_3.setBounds(89, 125, 130, 26);
		add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField(person.getPhone());
		textField_4.setBounds(90, 181, 130, 26);
		add(textField_4);
		textField_4.setColumns(10);
		
		textField_6 = new JTextField(person.getState());
		textField_6.setBounds(281, 125, 41, 26);
		add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField(person.getZip());
		textField_7.setBounds(361, 125, 68, 26);
		add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField(person.getSsn());
		textField_8.setBounds(253, 69, 130, 26);
		add(textField_8);
		textField_8.setColumns(10);
		
		textField_1 = new JTextField(person.getName());
		textField_1.setBounds(89, 97, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_5 = new JPasswordField(cashier.getPassword());
		textField_5.setBounds(90, 209, 130, 26);
		add(textField_5);
		textField_5.setColumns(10);

	}
	}

