package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSHI.ItemEditPanel;
import POSPD.Item;
import POSPD.Store;
import POSPD.UPC;

public class UPCEditPanel extends JPanel {
	private JTextField textField;
	
	/**
	 * Create the panel.
	 */
	public UPCEditPanel(JFrame currentFrame, Store store, Item item, UPC upc,boolean isItemAdd, boolean isAdd) {
		setLayout(null);
		
		JLabel lblEditUpc = new JLabel("Edit UPC");
		lblEditUpc.setBounds(177, 23, 70, 16);
		add(lblEditUpc);
		
		JLabel lblUpcCode = new JLabel("UPC CODE:");
		lblUpcCode.setBounds(42, 96, 83, 16);
		add(lblUpcCode);
		
		textField = new JTextField(upc.getUpc());
		textField.setBounds(132, 91, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !upc.getUpc().equals(textField.getText()))
				{
					store.removeUPC(upc);
					item.removeUPC(upc);
					upc.setUpc(textField.getText());
					upc.setItem(item);
					item.addUPC(upc);
					store.addUPC(upc);
				}
				if (isAdd) 
				{
					upc.setUpc(textField.getText());
					upc.setItem(item);
					item.addUPC(upc);
					store.addUPC(upc); 
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(68, 196, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame,store,item,isItemAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(217, 196, 117, 29);
		add(btnCancel);

	}
}
