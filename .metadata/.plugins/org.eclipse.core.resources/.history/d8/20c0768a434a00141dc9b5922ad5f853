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

}
