package test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.example.www.eurocal.EurocalSOAPImpl;
import org.example.www.eurocal.Eurocal_PortType;
import org.example.www.eurocal.Eurocal_ServiceLocator;

public class eurocalTest {
	public static void main(String[] args) {
		Eurocal_ServiceLocator service = new Eurocal_ServiceLocator();
		Eurocal_PortType stub;
		String currency = "USD";
		double amount = 50.0;
		double converted = 0.0;

		try { 
			stub = service.geteurocalSOAP();
		} catch(ServiceException e) {
			e.printStackTrace();
			return;
		}

		try {
			converted = stub.convertirAEuros(currency, amount);
			System.out.printf("%.2f %s is %.2f in Euros", amount, currency, converted);
		} catch (RemoteException e) {
			System.out.println("Servicio no disponible o fuera de línea. ");
			//return;
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		} 
		
	}
}
