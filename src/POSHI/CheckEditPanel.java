package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Check;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CheckEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public CheckEditPanel(JFrame currentFrame, Store store,Session session, Sale sale, Check check ) {
		setLayout(null);
		
		JLabel lblEnterCheck = new JLabel("Enter Check");
		lblEnterCheck.setBounds(174, 21, 109, 16);
		add(lblEnterCheck);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setBounds(65, 54, 61, 16);
		add(lblAmount);
		
		JLabel lblRoutingNumber = new JLabel("Routing Number :");
		lblRoutingNumber.setBounds(14, 87, 111, 16);
		add(lblRoutingNumber);
		
		JLabel lblAccountNumber = new JLabel("Account Number");
		lblAccountNumber.setBounds(14, 120, 111, 23);
		add(lblAccountNumber);
		
		JLabel lblChcekNumber = new JLabel("Chcek Number :");
		lblChcekNumber.setBounds(24, 156, 111, 16);
		add(lblChcekNumber);
		
		textField = new JTextField();
		textField.setBounds(154, 49, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 82, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(153, 115, 130, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(152, 151, 130, 26);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check.setAmtTendered(new BigDecimal(textField.getText()));
				check.setAmount(sale.calcAmount(new BigDecimal(textField.getText())));
				check.setRoutingNumber(textField_1.getText());
				check.setAccountNumber(textField_2.getText());
				check.setCheckNumber(textField_3.getText());
				
				sale.addPayment(check);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(65, 205, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(212, 205, 117, 29);
		add(btnCancel);

	}

}
