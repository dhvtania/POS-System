package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class SessionSummary extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public SessionSummary(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		
		JLabel lblSessionSummary = new JLabel("Session Summary");
		lblSessionSummary.setBounds(188, 18, 61, 16);
		add(lblSessionSummary);
		
		JLabel lblCashier = new JLabel("Cashier");
		lblCashier.setBounds(6, 54, 61, 16);
		add(lblCashier);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(6, 85, 61, 16);
		add(lblRegister);
		
		Label cashierlabel = new Label(session.getCashier().toString());
		cashierlabel.setBounds(73, 54, 93, 16);
		add(cashierlabel);
		
		Label RegisterLable = new Label(session.getRegister().toString());
		RegisterLable.setBounds(73, 85, 93, 16);
		add(RegisterLable);
		
		JLabel lblNumberSales = new JLabel("Number Sales :");
		lblNumberSales.setBounds(6, 134, 94, 16);
		add(lblNumberSales);
		
		JLabel lblTotalSales = new JLabel("Total Sales");
		lblTotalSales.setBounds(6, 170, 94, 16);
		add(lblTotalSales);
		
		JLabel lblEnterCash = new JLabel("Enter Cash :");
		lblEnterCash.setBounds(6, 202, 94, 16);
		add(lblEnterCash);
		
		JLabel lblCashCountDiff = new JLabel("Cash Count Diff :");
		lblCashCountDiff.setBounds(8, 232, 116, 16);
		add(lblCashCountDiff);
		
		JButton btnEndSession = new JButton("End Session");
		btnEndSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnEndSession.setBounds(170, 261, 117, 29);
		add(btnEndSession);
		
		textField = new JTextField(new Integer(session.getNumberSales()).toString());
		textField.setBounds(135, 129, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(session.calcTotal().toString());
		textField_1.setBounds(135, 165, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText(session.calcCashCountDiff(new BigDecimal(textField_2.getText())).toString());
			}
		});
		textField_2.setBounds(135, 197, 130, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(136, 227, 130, 26);
		add(textField_3);
		textField_3.setColumns(10);

	}
}
