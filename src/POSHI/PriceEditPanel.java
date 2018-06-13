package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.Price;
import POSPD.PromoPrice;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class PriceEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private boolean isPromo= false;
	private PromoPrice promoPrice = null;
	private Price price;
	private JLabel lblEndDate;
	private JTextField textField_3;

	/**
	 * Create the panel 
	 */
	public PriceEditPanel(JFrame currentFrame, Store store,Item item, Price priceIn, boolean isItemAdd, boolean isAdd) {
		price = priceIn;
		System.out.println(price.getClass());
		if (price.getClass().toString().equals("class POSPD.PromoPrice"))
		{
			isPromo = true;
			promoPrice = (PromoPrice) price;
		}
		
		setLayout(null);

		JLabel lblEditPrice = new JLabel("Edit Price");
		lblEditPrice.setBounds(160, 16, 72, 16);
		add(lblEditPrice);
	
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setBounds(43, 92, 61, 16);
		add(lblPrice);
		
		textField = new JTextField( );
		if (price.getPrice() != null)
			textField = new JTextField(price.getPrice().toString());
		textField.setBounds(140, 86, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date :");
		lblEffectiveDate.setBounds(43, 134, 95, 16);
		add(lblEffectiveDate);
		
		
		if (price.getEffectiveDate() != null) 
	
		
		textField_1 = new JTextField(price.getEffectiveDate().toString());
		textField_1.setBounds(140, 128, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				price.setPrice(new BigDecimal(textField.getText()));
				LocalDate ef;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				ef = LocalDate.parse(textField_1.getText(), formatter);
				price.setEffectiveDate(ef);
					
				if (isAdd) 
				{
					item.addPrice(price);
				}
				
				if (isPromo)
				{
					ef = LocalDate.parse(textField_1.getText());
					price.setEffectiveDate(ef);
					promoPrice.setEndDate(LocalDate.parse(textField_2.getText()));
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(69, 225, 117, 29);
		add(btnSave);
		
		/**
		 * Button Cancel takes user back to ItemEditPanel.
		 */
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(211, 225, 117, 29);
		add(btnCancel);
	
		JCheckBox chckbxPromoPrice = new JCheckBox("Promo Price");
		chckbxPromoPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxPromoPrice.isSelected()){
					isPromo = true;
					promoPrice = new PromoPrice();
					promoPrice.setPrice(price.getPrice());
					promoPrice.setEffectiveDate(price.getEffectiveDate());
					price = promoPrice;
					lblEndDate.setVisible(true);
					
					if(promoPrice.getEndDate() != null) {
						textField_2.setText(promoPrice.getEndDate().toString());
					}
					textField_2.setVisible(true);
				}
				else
				{
					isPromo=false;
					lblEndDate.setVisible(false);
					textField_2.setVisible(false);
					price = new Price();
					price.setPrice(promoPrice.getPrice());
					price.setEffectiveDate(promoPrice.getEffectiveDate());
				}
			}
		});
		
		chckbxPromoPrice.setBounds(127, 45, 128, 23);
		if (isPromo) chckbxPromoPrice.setSelected(true);
		else chckbxPromoPrice.setSelected(false);
		if (isAdd) chckbxPromoPrice.setEnabled(true);
		else chckbxPromoPrice.setEnabled(false);
		add(chckbxPromoPrice);

		lblEndDate = new JLabel("End Date :");
		lblEndDate.setBounds(43, 168, 84, 16);
		lblEndDate.setVisible(false);
		add(lblEndDate);

		textField_2 = new JTextField( );
		textField_2.setBounds(140, 162, 134, 28);
		textField_2.setVisible(false);
		add(textField_2);
		textField_2.setColumns(10);
		
		if (isPromo)
		{
			lblEndDate.setVisible(true);
			
			if (promoPrice.getEndDate() != null)
			textField_2.setText(promoPrice.getEndDate().toString());
			textField_2.setVisible(true);
		
		}
	}
}
