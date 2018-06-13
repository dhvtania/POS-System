package Test;

import java.time.LocalDate;

import POSPD.Cashier;
import POSPD.Item;
import POSPD.Register;
import POSPD.Sale;
import POSPD.Session;
import POSPD.Store;

public class StartStore {
	
	public void StartStore(Store store)
	{
		String list;
		//System.out.println("\n\n\nhello world");
		System.out.println(store.getName());
		try {
			POSDM.StoreDM.LoadStore(store);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Cashiers");
		System.out.println("\n==============\n");
		for(Cashier cashier : store.getCashiers().values())
		{
			System.out.println(cashier.getPerson().getName()+"\n");
		}
		System.out.println("\nRegisters");
		System.out.println("\n==============\n");
		
		for(Register R : store.getRegisters().values())
		{
			System.out.println(R.getNumber());
		}
		System.out.println("\nItems");
		System.out.println("\n==============\n");
		for(Item I : store.getItems().values()){
			System.out.println(I.getDescription()+" "+I.getPriceForDate(LocalDate.now())+" "+I.getTaxRateForDate(LocalDate.now()));
		}
		System.out.println("\nSessions");
		System.out.println("\n==============\n");
		for(Session S: store.getSessions()){
			
			System.out.println("Session : Cashier : "+S.getCashier().getPerson().getName()+" "+"Register : "+S.getRegister().getNumber());
			System.out.println("Total : "+S);
			/*for(Sale sale : S.getSales()){
				sSystem.out.println(sale);
			}*/
		}
	}
}