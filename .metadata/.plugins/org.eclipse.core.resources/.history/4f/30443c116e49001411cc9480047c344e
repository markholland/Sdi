package server;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


import rmi.EchoInt;

/**************************+
 * Parámetro de la VM (ejemplo)
 * -classpath c:\dya\ws\00_basico\prj-rmi\bin 
 * -Djava.rmi.server.codebase=file:///c:/dya/ws/00_basico/prj-rmi/bin/
 *
 */

public class EchoObjectRMI implements EchoInt {

	/**
	 * 
	 */

	protected EchoObjectRMI()  { //throws RemoteException
		super();
	}

	private static EchoObject eo = new EchoObject();

	public String echo(String input) {
		return eo.echo(input);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 //if (System.getSecurityManager() == null) {
		 //     System.setSecurityManager(new RMISecurityManager());
		 //}
	       try {
	            Registry registry = LocateRegistry.getRegistry();
	            EchoInt stub = (EchoInt) UnicastRemoteObject.exportObject(new EchoObjectRMI(),0);
	            registry.rebind("echo", stub);
	        } catch (RemoteException e) {
	            System.err.println("Something wrong happended on the remote end");
	            e.printStackTrace();
	            System.exit(-1); // can't just return, rmi threads may not exit
	        }
	        System.out.println("The echo server is ready");

	}
}
