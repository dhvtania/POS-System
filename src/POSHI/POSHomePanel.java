package POSHI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import POSPD.Store;

public class POSHomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	private JPanel contentPane;
	public POSHomePanel(Store store) {
		setLayout(null);
		
		
		JLabel lblStorename = new JLabel(store.getName());
		lblStorename.setBounds(84, 134, 237, 16);
		add(lblStorename);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(169, 95, 102, 16);
		add(lblWelcome);

	}
}
