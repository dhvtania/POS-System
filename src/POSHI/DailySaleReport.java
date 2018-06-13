package POSHI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

import POSPD.Cashier;
import POSPD.Item;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class DailySaleReport extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public DailySaleReport(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(23, 66, 42, 16);
		add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(77, 61, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("Go");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			LocalDate date = LocalDate.parse(textField.getText());
			String text = generateCashierReport(store,date);
			textField_1.setText(text);
			}
		});
		btnGo.setBounds(308, 61, 83, 29);
		add(btnGo);
		
		textField_1 = new JTextField();
		textField_1.setBounds(23, 110, 392, 135);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDailySaleReport = new JLabel("Daily Sale Report");
		lblDailySaleReport.setBounds(169, 18, 116, 31);
		add(lblDailySaleReport);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().add(new POSHomePanel(store));
			currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(146, 265, 117, 29);
		add(btnClose);

	}
	
	public String generateCashierReport(Store store, LocalDate date)
	{
		String report = "";
		report += "cashier Report : " +date +"\n";
		report += "\n";
		BigDecimal totalAmt = new BigDecimal("0.00");
		
		for(Cashier cashierElement : store.getCashiers().values())
		{
			Cashier cashier  = cashierElement;
			System.out.println();
			report += cashier.getNumber()+" "+cashier.getPerson().getName()+"\t\t"+cashier.calcNumberSales(date)+"\t"+cashier.calcDollarSales(date).toString()+"\n";
			totalAmt = totalAmt.add(cashier.calcDollarSales(date));
		}
		
		report += "\n" + "Total Amt : " + totalAmt;
		
		report += "\n\n" + "Item Report for :" +"" +date +"\n";
		
		int totalItems = 0;
		
		for (java.util.Map.Entry<String, Item> itemElement: store.getItems().entrySet())
		{
			Item item = itemElement.getValue();
			report += item.getNumber() + ""+ item.getDescription() + "\t\t" +item.calcItemSoldCount(date) + "\n";
			totalItems += item.calcItemSoldCount(date);
		}
		report += "\n" + "Total Items Sold:" +totalItems;
		return report;
		
	}
}
