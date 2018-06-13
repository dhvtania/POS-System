package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSHI.ItemListPanel;
import POSHI.PriceEditPanel;
import POSHI.UPCEditPanel;
import POSPD.Item;
import POSPD.Price;
import POSPD.Store;
import POSPD.TaxCategory;
import POSPD.UPC;

public class ItemEditPanel extends JPanel {
	
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private DefaultListModel upcListModel;
	private DefaultListModel priceListModel;
	private JButton btnEdit;
	private JButton btnEdit_1;
	private JButton btnDelete;
	private JButton btnDelete_1;


	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, boolean isAdd) {

setLayout(null);
JList upcList ;
		
		
		JLabel lblEditItem = new JLabel("Edit Item");
		lblEditItem.setBounds(189, 23, 56, 16);
		add(lblEditItem);
	
		JLabel lblItemNumber = new JLabel("Item Number : ");
		lblItemNumber.setBounds(6, 83, 94, 16);
		add(lblItemNumber);
		
		textField = new JTextField(item.getNumber());
		textField.setBounds(102, 77, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description : ");
		lblDescription.setBounds(6, 125, 94, 16);
		add(lblDescription);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(102, 119, 194, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTaxCategory = new JLabel("Tax Category : ");
		lblTaxCategory.setBounds(6, 168, 110, 16);
		add(lblTaxCategory);
	
		DefaultComboBoxModel tcList = new DefaultComboBoxModel(store.getTaxCategoryList().toArray());
		comboBox = new JComboBox(tcList);
		if(!isAdd) comboBox.setSelectedItem(item.getTaxCategory());
		comboBox.setBounds(102, 164, 134, 27);
		add(comboBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd && !item.getNumber().equals(textField.getText()))
				{
					store.removeItem(item);
					item.setNumber(textField.getText());
					store.addItem(item);;
				}
				if (isAdd) 
				{
					item.setNumber(textField.getText());
					store.addItem(item);
				}
				
				item.setDescription(textField_1.getText());
				item.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
			
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(36, 253, 117, 29);
		add(btnSave);
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(165, 253, 117, 29);
		add(btnCancel);
		
		//
		// upc
		
		upcListModel = new DefaultListModel();
		for (UPC upcEntry :item.getUpcs().values())
			upcListModel.addElement(upcEntry);
		
		
		upcList = new JList(upcListModel);
		upcList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (upcList.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
					btnDelete.setEnabled(true);
				}
			}
		});
		upcList.setBounds(304, 23, 128, 76);
		add(upcList);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPCEditPanel upcEditPanel = 
						new UPCEditPanel(currentFrame,store,item,(UPC)upcList.getSelectedValue(),isAdd,false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(upcEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(304, 102, 68, 29);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPCEditPanel upcEditPanel = 
						new UPCEditPanel(currentFrame,store,item,new UPC(),isAdd, true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(upcEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(370, 102, 62, 29);
		add(btnAdd);
		
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeUPC((UPC)upcList.getSelectedValue());
				item.removeUPC((UPC)upcList.getSelectedValue());
				upcListModel.removeElement(upcList.getSelectedValue());
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(337, 130, 75, 29);
		add(btnDelete);
		
		//
		// prices
		priceListModel = new DefaultListModel();
		for (Price price :item.getPrices() )
		priceListModel.addElement(price);
		
		JList priceList = new JList(priceListModel);
		priceList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (priceList.getSelectedValue() != null) 
				{
					btnEdit_1.setEnabled(true);
					btnDelete_1.setEnabled(true);
				}
			}
		});
		priceList.setBounds(304, 168, 128, 66);
		add(priceList);
		
		btnEdit_1 = new JButton("Edit");
		btnEdit_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceEditPanel priceEditPanel = 
						new PriceEditPanel(currentFrame,store,item,(Price)priceList.getSelectedValue(),isAdd,false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(priceEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit_1.setBounds(294, 246, 75, 29);
		btnEdit_1.setEnabled(false);
		add(btnEdit_1);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceEditPanel priceEditPanel = 
						new PriceEditPanel(currentFrame,store,item,new Price(),isAdd, true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(priceEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd_1.setBounds(370, 246, 62, 29);
		add(btnAdd_1);
		
		btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				item.removePrice((Price)priceList.getSelectedValue());
				priceListModel.removeElement(priceList.getSelectedValue());
			}
		});
		btnDelete_1.setEnabled(false);
		btnDelete_1.setBounds(337, 271, 68, 29);
		add(btnDelete_1);

	}
}
