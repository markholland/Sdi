package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

	/**d
	 * d
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String myURL = "localhost";
		
		 if (System.getSecurityManager() == null) {
		      System.setSecurityManager(new RMISecurityManager());
		 }
	       try {
	            Registry registry = LocateRegistry.getRegistry();
	            EchoInt stub = (EchoInt) UnicastRemoteObject.exportObject(new EchoObjectRMI(),0);
	            registry.rebind("echo", stub);
	            myURL=InetAddress.getLocalHost().getHostName();
	            //registry.rebind("//" + myURL + "/EchoObject", stub);
	        } catch (RemoteException e) {
	            System.err.println("Something wrong happended on the remote end");
	            e.printStackTrace();
	            System.exit(-1); // can't just return, rmi threads may not exit
	        } catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println("The echo server is ready");

	}
}
