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

import POSHI.CashieEditPanel;
import POSPD.Cashier;
import POSPD.Store;
import javax.swing.JScrollPane;

public class CashierListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JList list;
	DefaultListModel listModel;
	public CashierListPanel(JFrame currentFrame, Store store) {

setLayout(null);
		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashieEditPanel cashierEditPanel = 
						new CashieEditPanel(currentFrame,store, new Cashier(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cashierEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnAdd.setBounds(28, 217, 117, 29);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CashieEditPanel cashierEditPanel = 
						new CashieEditPanel(currentFrame,store, (Cashier)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(cashierEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(157, 217, 117, 29);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier((Cashier)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(294, 217, 117, 29);
		add(btnDelete);
		
		listModel = new DefaultListModel();
		for (Entry<String, Cashier> cashierEntry : store.getCashiers().entrySet())
		listModel.addElement(cashierEntry.getValue());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 22, 208, 169);
		add(scrollPane);
		
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				if (list.getSelectedValue() == null || ((Cashier)list.getSelectedValue()).isUsed())
				{
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
					
			}
			
		});

		
		
	}
}
