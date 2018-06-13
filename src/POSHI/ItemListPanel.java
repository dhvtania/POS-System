package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSHI.ItemEditPanel;
import POSPD.Item;
import POSPD.Store;
import javax.swing.JScrollPane;

public class ItemListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JButton btnEdit;
	JButton btnDelete_1;
	JButton btnAdd;
	JList list;
	private JScrollPane scrollPane;
	public ItemListPanel(JFrame currentFrame,Store store) {

setLayout(null);
		
		DefaultListModel listModel = new DefaultListModel();
		for (Entry<String, Item> itemEntry : store.getItems().entrySet())
		listModel.addElement(itemEntry.getValue());
		
		JLabel lblSelectItem = new JLabel("Select Item");
		lblSelectItem.setBounds(180, 26, 94, 16);
		add(lblSelectItem);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(124, 75, 199, 134);
		add(scrollPane);
		
		list = new JList(listModel);
		scrollPane.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) 
				{
					
					btnEdit.setEnabled(true);
				}
				Item si = (Item)list.getSelectedValue();
				if (list.getSelectedValue() == null || ((Item)list.getSelectedValue()).isUsed())
				{
					btnDelete_1.setEnabled(false);
				}
				else
				{
					btnDelete_1.setEnabled(true);
				}
			}
		});
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel itemEditPanel = 
						new ItemEditPanel(currentFrame,store,(Item)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(39, 218, 117, 29);
		add(btnEdit);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemEditPanel itemEditPanel = 
						new ItemEditPanel(currentFrame, store, new Item(), true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(itemEditPanel);
				currentFrame.revalidate();	
			}
		});
		btnAdd.setEnabled(true);
		btnAdd.setBounds(162, 218, 117, 29);
		add(btnAdd);
		
		btnDelete_1 = new JButton("Delete");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeItem((Item)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete_1.setEnabled(false);
		btnDelete_1.setBounds(298, 218, 117, 29);
		add(btnDelete_1);

	}

}