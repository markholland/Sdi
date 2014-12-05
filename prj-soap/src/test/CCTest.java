package test;

import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import NET.webserviceX.www.Currency;
import NET.webserviceX.www.CurrencyConvertorLocator;
import NET.webserviceX.www.CurrencyConvertorSoap;

public class CCTest {

	public static void main(String[] args) {
		CurrencyConvertorLocator service;
		CurrencyConvertorSoap stub;
		double rate = 0;
		service = new CurrencyConvertorLocator();
		try {
			stub = service.getCurrencyConvertorSoap();
		} catch (ServiceException e) {
			e.printStackTrace();
			return;
		}
		try {
			rate = stub.conversionRate(Currency.fromString("EUR"), Currency.USD);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println("Con un euro puedes comprar "+rate+" dólares.");
	}
}
