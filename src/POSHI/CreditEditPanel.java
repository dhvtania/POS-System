package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Credit;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;

public class CreditEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public CreditEditPanel(JFrame currentFrame, Store store,Session session, Sale sale, Credit credit ) {
		setLayout(null);
		
		JLabel lblCreditCardPayment = new JLabel("Credit Card Payment");
		lblCreditCardPayment.setBounds(151, 6, 149, 16);
		add(lblCreditCardPayment);
		
		JLabel lblAmount = new JLabel("Amount :");
		lblAmount.setBounds(50, 51, 61, 16);
		add(lblAmount);
		
		textField = new JTextField();
		textField.setBounds(136, 46, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCardType = new JLabel("Card Type :");
		lblCardType.setBounds(39, 91, 83, 16);
		add(lblCardType);
		
		String[] cardType = {"Visa","Master","Discover","Private"};
		JComboBox comboBox = new JComboBox(cardType);
		comboBox.setBounds(136, 87, 130, 27);
		add(comboBox);
		
		JLabel lblAccNumber = new JLabel("Acc Number :");
		lblAccNumber.setBounds(28, 134, 93, 16);
		add(lblAccNumber);
		
		textField_1 = new JTextField();
		textField_1.setBounds(136, 126, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblExpireDate = new JLabel("Expire Date :");
		lblExpireDate.setBounds(29, 172, 93, 16);
		add(lblExpireDate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(136, 167, 130, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				credit.setAmount(new BigDecimal(textField.getText()));
				credit.setCardType((String)comboBox.getSelectedItem());
				credit.setAcctNumber(textField_1.getText());
				String[] ed = textField_2.getText().split("/");
//				credit.setExpireDate(new LocalDate(ed));
//				ef = LocalDate.parse(textField.getText(), formatter);
				
				sale.addPayment(credit);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(50, 200, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(212, 200, 117, 29);
		add(btnCancel);

	}
}
