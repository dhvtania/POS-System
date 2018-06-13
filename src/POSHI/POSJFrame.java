package POSHI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import POSHI.CashierListPanel;
import POSHI.ItemListPanel;
import POSHI.POSHomePanel;
import POSHI.POSJFrame;
import POSHI.POSLogin;
import POSHI.RegisterListPanel;
import POSHI.StoreEditPanel;
import POSHI.TaxCategoryListPanel;
import POSPD.Store;

public class POSJFrame extends JFrame {

	private JPanel contentPane;
	private JFrame currentFrame;

	/**
	 * Launch the application.
	 */
	public static void run(final Store store) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSJFrame frame = new POSJFrame(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public POSJFrame(final Store store) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 450);
		currentFrame = this;
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaintain = new JMenu("Maintain");
		menuBar.add(mnMaintain);
		
		JMenuItem mntmStore = new JMenuItem("Store");
		mntmStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				 getContentPane().add(new StoreEditPanel(currentFrame,store));
				 getContentPane().revalidate();

			}
		});
		mnMaintain.add(mntmStore);
		
		JMenuItem mntmCashier = new JMenuItem("Cashier");
		mntmCashier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmCashier);
		
		JMenuItem mntmRegister = new JMenuItem("Register");
		mntmRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new RegisterListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmRegister);
		
		JMenuItem mntmTaxCatergory = new JMenuItem("Tax Catergory");
		mntmTaxCatergory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new TaxCategoryListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmTaxCatergory);
		
		JMenuItem mntmItem = new JMenuItem("Item");
		mntmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemListPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmItem);
		
		JMenu mnPos = new JMenu("POS");
		menuBar.add(mnPos);
		
		JMenuItem mntmLogin = new JMenuItem("Login");
		mntmLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POSLogin(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnPos.add(mntmLogin);
		
		JMenuItem mntmSaleentry = new JMenuItem("SaleEntry");
		mnPos.add(mntmSaleentry);
		
		JMenuItem mntmPaymentEntry = new JMenuItem("Payment Entry");
		mnPos.add(mntmPaymentEntry);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmCashierReport = new JMenuItem("Cashier Report");
		mntmCashierReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmCashierReport);
		
		JMenuItem mntmItemReport = new JMenuItem("Item Report");
		mntmItemReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemReportPanel(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmItemReport);
		
		JMenuItem mntmDailyReport = new JMenuItem("Daily Report");
		mntmDailyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new DailySaleReport(currentFrame,store));
				getContentPane().revalidate();
			}
		});
		mnReports.add(mntmDailyReport);
		
		contentPane = new POSHomePanel(store);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
