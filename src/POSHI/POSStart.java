package POSHI;

import javax.swing.JPanel;

import POSHI.POSJFrame;
import POSPD.Store;

public class POSStart extends JPanel {

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Store store = new Store();
		try {
			POSDM.StoreDM.LoadStore(store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		POSJFrame.run(store);

	}

}