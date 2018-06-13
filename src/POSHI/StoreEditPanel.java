package POSHI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import POSHI.POSHomePanel;
import POSPD.Store;

public class StoreEditPanel extends JPanel {

	private JTextField textField;
	/**
	 * Create the panel.
	 */
public StoreEditPanel(JFrame currentFrame, Store store ) {
		
		setLayout(null);
		
		textField = new JTextField(store.getName());
		textField.setBounds(213, 56, 130, 26);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(85, 61, 61, 16);
		add(lblName);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				store.setName(textField.getText());
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(64, 185, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(226, 185, 117, 29);
		add(btnCancel);
	}
}