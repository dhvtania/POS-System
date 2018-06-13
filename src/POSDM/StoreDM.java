
package POSDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import POSPD.*;

public class StoreDM {
	
	public static void LoadStore(Store myStore) throws Exception
	{
		String fileName ="StoreData_v2.csv";
		Session session = null;
		Sale sale = null;
		String line = null; 
			
			try {
				
				FileReader fileReader = new FileReader(fileName);			
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
			
				while((line = bufferedReader.readLine()) != null)
				{
					String [] result = line.split(",");
					switch (result[0]) 
					{
						case "Store":
							if (myStore != null)
								myStore.setName(result[1]);
							break;
						case "TaxCategory": 
							TaxCategory taxCategory = new TaxCategory(result[1]);
							
							TaxRate taxRate = new TaxRate(result[2],result[3]);
							taxCategory.addTaxRate(taxRate);
							myStore.addTaxCategory(taxCategory);
							break;
						case "Item":	
							Item item = new Item(myStore,result[1],result[3],result[4]);
							UPC upc = new UPC(result[2],item);
							item.addUPC(upc);
							item.addPrice(new Price(result[5],result[6]));
							if (result.length > 7)	
							{ 
		        				PromoPrice promoPrice = new PromoPrice(item, result[7],result[8],result[9]);
		        				item.addPrice(promoPrice);
		        			}
							myStore.addItem(item);
							myStore.addUPC(upc);
							break;
						case "Cashier":	
							Cashier cashier = new Cashier(result[1],result[9]);
							cashier.setPerson(new Person(result[2], "#"+result[4], result[5], result[6], result[7], result[8], result[3]));
							myStore.addCashier(cashier);
							break;
						case "Register": 
							Register register = new Register(result[1]);
							myStore.addRegister(register);
							break;
						case "Session":	
							session = new Session(myStore,result[1],result[2]);
							break;
						case "Sale":
							sale = new Sale(session, result[1]);
							session.addSale(sale);
							break;
						case "SaleLineItem":
							new SaleLineItem(myStore,sale,result[1],result[2]);
							break;
						case "Payment":
							switch(result[1])
							{
								case "Cash":
									new Cash(sale,result[2],result[3]);
									break;
								case "Check":
									new Check(sale, result[2], result[4], result[5], result[3]);
									break;
								case "Credit":
									new Credit(sale, result[2],result[4], result[5], result[6]);
									break;
								default:
										break;
							}
							break;
						default:	
							System.out.println("Not Sure where this goes: " + result[0] + " ");
							break;
					}
				}
				
				bufferedReader.close();
			}
			catch (FileNotFoundException ex)	
			{
				System.out.println("Unable to open file: '" + fileName + "'");
			}
			catch (IOException ex)	
			{
				System.out.println("Error reading file: '" + fileName + "'");
			}
			
	}
	
	
}                  