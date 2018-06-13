package POSHI;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.Sale;
import POSPD.SaleLineItem;
import POSPD.Session;
import POSPD.Store;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Label;

public class POSSaleEntry extends JPanel {
	
	protected static final String Integet = null;
	private JLabel lblCashier;
	private JLabel lblRegister;
	private JLabel label;
	private JLabel label_1;
	private JLabel lblNewLabel;
	private JLabel lblSale;
	private JLabel lblItem;
	private JLabel lblQuantity;
	private JTextField quantityfield;
	private JTextField itemField;
	private JTextField subtotaltextField;
	private JTextField taxtextField;
	private JTextField totaltextField;
	private JLabel lblAmountTendered;
	private JTextField amttendertextField;
	private JLabel lblChange;
	private JTextField changetextField;
	private JButton btnPayment;
	private JButton btnCompleteSale;
	private JButton btnCancelSale;
	private JButton btnEndSession;
	private Label lblMessage;
	private Sale currentSale;
	private DefaultListModel<SaleLineItem> listModel;
	private JList list;

	/**
	 * Create the panel.
	 */

	public POSSaleEntry(JFrame currentFrame, Store store, Session session,Sale sale) {
		setLayout(null);
		currentSale = sale;
		
				lblCashier = new JLabel("Cashier :");
				lblCashier.setBounds(6, 13, 61, 16);
				add(lblCashier);
				
				lblRegister = new JLabel("Register :");
				lblRegister.setBounds(6, 41, 61, 16);
				add(lblRegister);
				
				label = new JLabel(session.getCashier().toString());
				label.setBounds(77, 13, 86, 16);
				add(label);

				label_1 = new JLabel(session.getRegister().getNumber());
				label_1.setBounds(77, 41, 86, 16);
				add(label_1);
				
				lblSale = new JLabel("Sale");
				lblSale.setBounds(227, 13, 61, 16);
				add(lblSale);
				
				lblItem = new JLabel("Item :");
				lblItem.setBounds(6, 89, 61, 16);
				add(lblItem);
				
				itemField = new JTextField();
				itemField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Item item = store.findItemForUPC(itemField.getText());
						if (item ==null)
						{  
							itemField.setText(" ");
							lblMessage.setText("Item not found. Enter UPC again.");
							itemField.requestFocusInWindow();
						}
						else
						{
							int quantity = Integer.parseInt(quantityfield.getText());
							SaleLineItem sli = new SaleLineItem(currentSale,item,quantity);
							listModel.addElement(sli);
							subtotaltextField.setText(currentSale.calcSubTotal().toString());
							taxtextField.setText(currentSale.calcTax().toString());
							totaltextField.setText(currentSale.calcTotal().toString());
							itemField.setText("");
							lblMessage.setText("");
							quantityfield.setText("1");
							if (currentSale.isPaymentEnough()) btnCompleteSale.setEnabled(true);
							else btnCompleteSale.setEnabled(false);
							itemField.requestFocusInWindow();
							btnEndSession.setEnabled(false);
					}
					}
				});
				itemField.setBounds(48, 84, 130, 26);
				add(itemField);
				
				itemField.setColumns(10);
				lblQuantity = new JLabel("Quantity :");
				lblQuantity.setBounds(189, 89, 70, 16);
				add(lblQuantity);
				
				quantityfield = new JTextField("1");
				quantityfield.setBounds(271, 84, 130, 26);
				add(quantityfield);
				quantityfield.setColumns(10);
				
				JCheckBox chckbxTaxFree = new JCheckBox("Tax Free");
				chckbxTaxFree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (chckbxTaxFree.isSelected())
						{
							currentSale.setTaxFree(true);
						}
						else
						{
							currentSale.setTaxFree(false);
						}
						taxtextField.setText(sale.calcTax().toString());
						totaltextField.setText(sale.calcTotal().toString());
					}
				});
				chckbxTaxFree.setBounds(344, 34, 128, 23);
				add(chckbxTaxFree);
				
				listModel = new DefaultListModel();
				for (SaleLineItem sli : currentSale.getSaleLineItems()) listModel.addElement(sli);
				list = new JList(listModel);
				list.setBounds(16, 117, 280, 106);
				add(list);
				
				JLabel lblSubtotal = new JLabel("SubTotal :");
				lblSubtotal.setBounds(340, 126, 70, 16);
				add(lblSubtotal);
				
				JLabel lblTax = new JLabel("Tax :");
				lblTax.setBounds(340, 163, 61, 16);
				add(lblTax);
				
				JLabel lblTotal = new JLabel("Total :");
				lblTotal.setBounds(344, 195, 61, 16);
				add(lblTotal);
				
				subtotaltextField = new JTextField(sale.calcSubTotal().toString());
				subtotaltextField.setBounds(413, 121, 130, 26);
				add(subtotaltextField);
				subtotaltextField.setColumns(10);
				
				taxtextField = new JTextField(sale.calcTax().toString());
				taxtextField.setBounds(413, 158, 130, 26);
				add(taxtextField);
				taxtextField.setColumns(10);
				
				totaltextField = new JTextField(sale.calcTotal().toString());
				totaltextField.setBounds(413, 190, 130, 26);
				add(totaltextField);
				totaltextField.setColumns(10);
				
				lblAmountTendered = new JLabel("Amt Tendered :");
				lblAmountTendered.setBounds(306, 271, 105, 16);
				add(lblAmountTendered);
				
				amttendertextField = new JTextField(sale.calcAmtTendered().toString());
				amttendertextField.setBounds(413, 266, 130, 26);
				add(amttendertextField);
				amttendertextField.setColumns(10);
				
				lblChange = new JLabel("Change :");
				lblChange.setBounds(306, 309, 61, 16);
				add(lblChange);
				
				changetextField = new JTextField(sale.calcChange().toString());
				changetextField.setBounds(413, 304, 130, 26);
				add(changetextField);
				changetextField.setColumns(10);
				
				btnPayment = new JButton("Payment");
				btnPayment.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new PaymentPanel(currentFrame,store, session,sale));
						currentFrame.getContentPane().revalidate();
					}
				});
				btnPayment.setBounds(6, 266, 117, 29);
				add(btnPayment);
				
				btnCompleteSale = new JButton("Complete Sale");
				btnCompleteSale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						session.addSale(currentSale);
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new POSSaleEntry(currentFrame,store,session,new Sale()));
						currentFrame.getContentPane().revalidate();
					}
				});
				if (currentSale.isPaymentEnough()) btnCompleteSale.setEnabled(true);
				else btnCompleteSale.setEnabled(false);
				btnCompleteSale.setBounds(115, 226, 117, 29);
				add(btnCompleteSale);
				
				
				btnCompleteSale.setBounds(135, 269, 117, 29);
				add(btnCompleteSale);
				
				btnCancelSale = new JButton("Cancel Sale");
				btnCancelSale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new POSSaleEntry(currentFrame,store,session,new Sale()));
						currentFrame.getContentPane().revalidate(); 
					}
				});
				btnCancelSale.setBounds(6, 304, 117, 29);
				add(btnCancelSale);
				
				btnEndSession = new JButton("End Session");
				btnEndSession.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new SessionSummary(currentFrame, store,session));
						currentFrame.getContentPane().revalidate();
					}
				});
				btnEndSession.setBounds(135, 304, 117, 29);
				add(btnEndSession);
				
				lblMessage = new Label("");
				lblMessage.setBounds(51, 63, 309, 20);
				add(lblMessage);
		

	}
}