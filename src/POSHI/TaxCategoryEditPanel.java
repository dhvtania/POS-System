package POSHI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import POSPD.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	private JList list;

	/**
	 * Create the panel 
	 */
	JButton btnEdit;
	JButton btnDelete;
	JButton btnSave;
	DefaultListModel listModel;
	private JScrollPane scrollPane;
	
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Category :");
		lblNewLabel.setBounds(56, 76, 86, 16);
		add(lblNewLabel);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(154, 70, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		///
		
		listModel = new DefaultListModel();
		for (TaxRate tax : taxCategory.getTaxRate()){
			listModel.addElement(tax);
		}
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 115, 264, 113);
		add(scrollPane);
		
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null)
				{
					btnEdit.setEnabled(true);	
				}
				if(list.getSelectedValue() == null)
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
			}
		});
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			TaxRatePanel taxratepanel = new TaxRatePanel(currentFrame,store,taxCategory,new TaxRate(), isAdd, true);
			currentFrame.getContentPane().removeAll();
			currentFrame.getContentPane().add(taxratepanel);
			currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(308, 110, 117, 29);
		add(btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxRatePanel taxratepanel = new TaxRatePanel(currentFrame,store,taxCategory, (TaxRate)list.getSelectedValue(), isAdd, false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(taxratepanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(309, 162, 117, 29);
		add(btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.removeTaxRate((TaxRate) list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(308, 217, 117, 29);
		add(btnDelete);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isAdd)
				{
					taxCategory.setCategory(textField.getText());
				}
				if (isAdd) 
				{
					taxCategory.setCategory(textField.getText());
					store.addTaxCategory(taxCategory);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(35, 253, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryListPanel(currentFrame,store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(171, 253, 117, 29);
		add(btnCancel);
		
		JLabel lblTaxCategoryEdit = new JLabel("Tax Category Edit");
		lblTaxCategoryEdit.setBounds(124, 25, 117, 16);
		add(lblTaxCategoryEdit);

	}
}
