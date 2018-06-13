package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.Cashier;
import POSPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CashierReportPanel extends JPanel {
	private JTextPane textField_1;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CashierReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblCashierReport = new JLabel("Cashier Report");
		lblCashierReport.setBounds(189, 31, 100, 16);
		add(lblCashierReport);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(56, 73, 61, 16);
		add(lblDate);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(166, 244, 117, 29);
		add(btnClose);
		
		textField = new JTextField();
		textField.setBounds(108, 68, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("GO");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate date = LocalDate.parse(textField.getText());
				String data = generateCashierReport(store,date);
				textField_1.setText(data);
				
			}
			public String generateCashierReport(Store store, LocalDate date)
			{
				String report = "";
				report  += "\nCashier Report for :" +"\n";
				report += "\n";
				BigDecimal totalAmt = new BigDecimal("0.00");
				
				for (Cashier cashierElement : store.getCashiers().values())
				{
					Cashier cashier = cashierElement;
					report += cashier.getNumber()+ "\t" + cashier.getPerson().getName()+"\t"+"\t"+cashier.calcNumberSales(date)+"\t"+cashier.calcDollarSales(date).toString()+"\n";
					totalAmt = totalAmt.add(cashier.calcDollarSales(date));
				}
				report += "\n" + "Total Amt:" +totalAmt;
				return report;

			}
			
		});
		btnGo.setBounds(344, 68, 70, 29);
		add(btnGo);
		
		textField_1 = new JTextPane();
		textField_1.setBounds(19, 101, 407, 131);
		add(textField_1);
//		textField_1.setColumns(10);	
		
	}
}
