package engine;

import java.rmi.*;
import java.rmi.server.*;
import compute.*;

public class ComputeEngine extends UnicastRemoteObject implements Compute {
	
	public ComputeEngine() throws RemoteException {
		super();
	}
	
	public Object executeTask(Task t) {
		return t.execute();
	}
	
	public static void main(String[] args) {
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		String name = "//localhost/Compute";
		try {
			Compute engine = new ComputeEngine();
			Naming.rebind(name, engine);
			System.out.println("ComputeEngine bound");
		} catch(Exception e) {
			System.out.println("Failed to bind ComputeEngine!");
			e.printStackTrace();
		}
	}

	@Override
	public void loadTask(Task t) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
 
}