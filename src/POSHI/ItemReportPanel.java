package POSHI;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POSPD.Item;
import POSPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class ItemReportPanel extends JPanel {
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public ItemReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblItemReport = new JLabel("Item Report");
		lblItemReport.setBounds(203, 24, 78, 16);
		add(lblItemReport);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(72, 64, 61, 16);
		add(lblDate);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate ef;
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				ef = LocalDate.parse(textField.getText(), formatter);
			String text = itemreport(store,ef);
			textArea.setText(text);
			}
		});
		textField.setBounds(151, 59, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnClose.setBounds(178, 250, 117, 29);
		add(btnClose);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(18, 92, 403, 146);
		add(textPane);
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			LocalDate date = LocalDate.parse(textField.getText());
			String text = itemreport(store,date);
			textPane.setText(text);
			}
		});
		btnNewButton.setBounds(329, 59, 92, 29);
		add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(88, 87, 265, 161);
	}
		
		public String itemreport(Store store, LocalDate date)
		{
			String report = "";
			report += "Item report for: "+" "+date +"\n";
			report += "\n";
			int totalItems = 0;
			for (Entry<String, Item> itemElement: store.getItems().entrySet())
			{
				Item item = itemElement.getValue();
				report += item.getNumber()+" "+item.getDescription()+ "\t" + "\t" +item.calcItemSoldCount(date)+ "\n";
				totalItems += item.calcItemSoldCount(date);
			}
			report += "\n" + "total Items Sold:" +totalItems;
			return report;

	}
}
