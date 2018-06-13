package POSHI;

import javax.swing.JPanel;
import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class TaxRatePanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yy");

	/**
	 * Create the panel.
	 */
	public TaxRatePanel(JFrame currentFrame, Store store, TaxCategory taxCategory, TaxRate tax, boolean isAdd, boolean isTax ) {
		setLayout(null);
		
		JLabel lblRate = new JLabel("Rate :");
		lblRate.setBounds(64, 69, 61, 16);
		add(lblRate);
		
		JLabel lblEffectiveDate = new JLabel("Effective Date :");
		lblEffectiveDate.setBounds(6, 115, 107, 16);
		add(lblEffectiveDate);
		
		textField = new JTextField();
		String taxgetEffectiveDate = tax.getEffectiveDate().toString();
		textField.setText(taxgetEffectiveDate);
		textField.setBounds(159, 64, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText(tax.getTaxRate().toString());
		textField_1.setBounds(159, 110, 130, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			LocalDate ef;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			ef = LocalDate.parse(textField.getText(), formatter);
			tax.setEffectiveDate(ef);
			tax.setTaxRate(new BigDecimal(textField_1.getText()));
			if (isTax){
				taxCategory.addTaxRate(tax);
			}
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame,store, taxCategory, isAdd));
			currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(66, 218, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame,store, taxCategory, isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(244, 218, 117, 29);
		add(btnCancel);
		
		JLabel lblTaxRate = new JLabel("Tax Rate");
		lblTaxRate.setBounds(176, 19, 61, 16);
		add(lblTaxRate);

	}

}
