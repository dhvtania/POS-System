package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cash;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CshEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CshEditPanel(JFrame currentFrame, Store store, Session session, Sale sale, Cash cash) {
		setLayout(null);
		
		JLabel lblCashPayment = new JLabel("Cash Payment");
		lblCashPayment.setBounds(162, 27, 138, 16);
		add(lblCashPayment);
		
		JLabel lblAmountTendered = new JLabel("Amount Tendered :");
		lblAmountTendered.setBounds(55, 96, 131, 16);
		add(lblAmountTendered);
		
		textField = new JTextField();
		textField.setBounds(229, 91, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cash.setAmtTendered(new BigDecimal(textField.getText()));
				cash.setAmount(sale.calcAmount(new BigDecimal(textField.getText())));
//				System.out.println("Cash Payment Amount :"+cash.getAmount().toString());
				System.out.println(cash.getAmtTendered());
				sale.addPayment(cash);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(85, 176, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(214, 176, 117, 29);
		add(btnCancel);

	}

}
