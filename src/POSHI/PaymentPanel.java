package POSHI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Cash;
import POSPD.Check;
import POSPD.Credit;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class PaymentPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public PaymentPanel(JFrame currentFrame, Store store, Session session, Sale sale) {
		setLayout(null);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setBounds(183, 6, 73, 23);
		add(lblPayment);
		
		JLabel lblPaymentDue = new JLabel("Payment Due :");
		lblPaymentDue.setBounds(8, 43, 90, 23);
		add(lblPaymentDue);
		
		textField = new JTextField(sale.calcTotal().toString());
//		System.out.println(sale.calcTotal());
		textField.setEditable(false);
		textField.setBounds(8, 66, 124, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblAmountTendered = new JLabel("Amount Tendered :");
		lblAmountTendered.setBounds(8, 102, 130, 16);
		add(lblAmountTendered);
		
		textField_1 = new JTextField(sale.calcAmtTendered().toString());
		textField_1.setEditable(false);
		textField_1.setBounds(8, 126, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = new CshEditPanel(currentFrame, store, session, sale, new Cash());
				panel.setBounds(121, 20, 373, 344);
				add(panel);
				revalidate();
				repaint();
			}
		});
		btnCash.setBounds(8, 171, 117, 29);
		add(btnCash);
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = new CreditEditPanel(currentFrame, store, session, sale, new Credit());
				panel.setBounds(171, 20, 323, 344);
				add(panel);
				revalidate();
				repaint();
			}
		});
		btnCredit.setBounds(8, 212, 117, 29);
		add(btnCredit);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = new CheckEditPanel(currentFrame, store, session, sale, new Check());
				panel.setBounds(171, 20, 323, 344);
				add(panel);
				revalidate();
				repaint();
			}
		});
		btnCheck.setBounds(8, 249, 117, 29);
		add(btnCheck);
		
		JButton btnCompletePayment = new JButton("Complete Payment");
		btnCompletePayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSaleEntry(currentFrame,store,session,sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCompletePayment.setBounds(183, 271, 146, 29);
		if (sale.calcAmtTendered().compareTo(sale.calcTotal()) >= 0)
			btnCompletePayment.setEnabled(true);
		else btnCompletePayment.setEnabled(false);
		add(btnCompletePayment);

	}
}
