package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSHI.POSHomePanel;
import POSHI.POSSaleEntry;
import POSPD.Cashier;
import POSPD.Register;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JPasswordField;
import java.awt.Label;

public class POSLogin extends JPanel {
	
	private JTextField CashField;
	private JPasswordField passwordField;
	private JComboBox cashiercomboBox;
	private JComboBox registercomboBox;
	private Label lblMessageLabel_1;
	private DefaultComboBoxModel cashierComboList;
	private DefaultComboBoxModel registerComboList;

	/**
	 * Create the panel.
	 */
	public POSLogin(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(183, 37, 61, 16);
		add(lblLogin);
		
		JLabel lblCashierNumber = new JLabel("Cashier Number : ");
		lblCashierNumber.setBounds(30, 86, 113, 16);
		add(lblCashierNumber);
		
		JLabel lblRegisterNumber = new JLabel("Register Number :");
		lblRegisterNumber.setBounds(30, 127, 113, 16);
		add(lblRegisterNumber);
		
		JLabel lblStartingCash = new JLabel("Starting Cash :");
		lblStartingCash.setBounds(30, 171, 113, 16);
		add(lblStartingCash);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(183, 209, 130, 26);
		add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(30, 214, 113, 16);
		add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = (Cashier) cashiercomboBox.getSelectedItem();
				Register register = (Register) registercomboBox.getSelectedItem();
				
				if (cashier.isAuthorized(passwordField.getText()))
				{
					Session session = new Session(store,cashier,register);
					session.getRegister().getCashDrawer().setCashAmount(new BigDecimal(CashField.getText()));
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSSaleEntry(currentFrame,store,session,new Sale()));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					lblMessageLabel_1.setText("Invalid Password for Number. Try Again");
				}
			}
		});
		btnLogin.setBounds(82, 253, 117, 29);
		add(btnLogin);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(253, 253, 117, 29);
		add(btnCancel);
		
		CashField = new JTextField();
		CashField.setBounds(183, 166, 130, 26);
		add(CashField);
		CashField.setColumns(10);
	
		DefaultComboBoxModel cashierList = new DefaultComboBoxModel(store.getCashierList().toArray());
		cashiercomboBox = new JComboBox(cashierList);
		cashiercomboBox.setBounds(183, 82, 130, 27);
		add(cashiercomboBox);
		
		DefaultComboBoxModel registerList = new DefaultComboBoxModel(store.getRegisterList().toArray());
		registercomboBox = new JComboBox(registerList);
		registercomboBox.setBounds(183, 123, 130, 27);
		add(registercomboBox);
		
		lblMessageLabel_1 = new Label("");
		lblMessageLabel_1.setBounds(82, 59, 262, 16);
		add(lblMessageLabel_1);

	}
}
