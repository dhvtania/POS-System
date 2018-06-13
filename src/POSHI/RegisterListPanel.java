package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import POSHI.RegisterEditPanel;
import POSPD.Register;
import POSPD.Store;
import POSPD.TaxCategory;

public class RegisterListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private DefaultListModel listModel;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;
	private JList list;
	TaxCategory taxcatergory = new TaxCategory();
	public RegisterListPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblSelectRegister = new JLabel("Select Register");
		lblSelectRegister.setBounds(171, 32, 101, 29);
		add(lblSelectRegister);
		
		
		
		listModel = new DefaultListModel();
		for (Register reg : store.getRegisters().values())
		listModel.addElement(reg);
		
	    list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedValue() != null) 
				{
					btnEdit.setEnabled(true);
				}
				
				if (list.getSelectedValue() == null || store.isRegisterUsed((Register)list.getSelectedValue()))
				{
					
					btnDelete.setEnabled(false);
				}
				else
				{
					btnDelete.setEnabled(true);
				}
					
			}
		});
		list.setBounds(136, 73, 179, 135);
		add(list);
		
		
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterEditPanel registerEditPanel = 
						new RegisterEditPanel(currentFrame,store, (Register)list.getSelectedValue(), false);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerEditPanel);
				currentFrame.revalidate();
			}
		});
		btnEdit.setBounds(27, 220, 117, 29);
		btnEdit.setEnabled(false);
		add(btnEdit);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = new Register();
				RegisterEditPanel registerEditPanel = 
						new RegisterEditPanel(currentFrame,store,register, true);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(registerEditPanel);
				currentFrame.revalidate();
			}
		});
		btnAdd.setBounds(171, 220, 117, 29);
		add(btnAdd);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeRegister((Register)list.getSelectedValue());
				listModel.removeElement(list.getSelectedValue());
			}
		});
		btnDelete.setBounds(310, 220, 117, 29);
		btnDelete.setEnabled(false);
		add(btnDelete);

	}

}
