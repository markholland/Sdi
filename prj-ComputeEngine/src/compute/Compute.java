package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {
	//executeTask: Run a task directly and straight away.
	Object executeTask(Task t) throws RemoteException; 
	
    //loadTask: Cargar una nueva task en el ComputeEngine. No ejecutarla.
	void loadTask(Task t) throws RemoteException; 
	
	//executeTask: Ejecutar una task previamente cargada con loadTask
	//la task admite como argumentos de entrada los proporcionados en arg y 
	//el resultado de la Task es devuelto como resultado de executeTask
	Object execute() throws RemoteException; 
}
