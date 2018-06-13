package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSHI.TaxCategoryEditPanel;
import POSPD.Store;
import POSPD.TaxCategory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class TaxCategoryListPanel extends JPanel {

	
	JList list;
	JButton btnDelete;
	JButton btnEdit;
	DefaultListModel listModel;
	/**
	 * Create the panel.
	 */
	public TaxCategoryListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategoryEditPanel tcEditPanel = 
						new TaxCategoryEditPanel(currentFrame,store, (TaxCategory)list.getSelectedValue(),  false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(tcEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(30, 231, 117, 29);
		add(btnEdit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategoryEditPanel tcEditPanel = 
						new TaxCategoryEditPanel(currentFrame,store, new TaxCategory(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(tcEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(152, 231, 117, 29);
		add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeTaxCategory((TaxCategory)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(281, 231, 117, 29);
		add(btnDelete);

		listModel = new DefaultListModel();
		for (Entry<String, TaxCategory> tcEntry : store.getTaxCategories().entrySet())
		listModel.addElement(tcEntry.getValue());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(152, 55, 152, 149);
		add(scrollPane);
		
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				
				if (list.getSelectedValue() == null || store.isTaxCategoryUsed((TaxCategory)list.getSelectedValue()))
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
					
			}
		});
		
		JLabel lblTaxCategories = new JLabel("Tax Categories");
		lblTaxCategories.setBounds(152, 16, 152, 16);
		add(lblTaxCategories);
		
	}
}
