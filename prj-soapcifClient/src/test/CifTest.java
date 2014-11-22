package test;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import services.Cifrador;
import services.CifradorServiceLocator;


public class CifTest {
	public static void main(String[] args) {
		CifradorServiceLocator service = new CifradorServiceLocator();; 
		Cifrador stub; 
		String code = "";
		String decode = "";
		
		try { 
			stub = service.getCifrador(); 
		}	catch (ServiceException e) { 
			e.printStackTrace(); 
			return;
		}
		try {
		    code = stub.cifra("ABCDEFG", "1234");
		    decode = stub.descifra(code, "1234");
		} catch (RemoteException e) {
			System.out.println("Servicio no disponible o fuera de línea. ");
			return;
			//e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return;
		}
		System.out.println("\""+decode+"\" cifrado es: "+code);
	}
}


