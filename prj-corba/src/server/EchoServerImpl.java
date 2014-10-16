package server;

import java.rmi.RemoteException;

/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 */
public class EchoServerImpl extends corba.EchoPOA {
	/**
	 * Constructor for EchoServerImpl 
	 */
	EchoObject eo = new EchoObject();
	public EchoServerImpl() {
	}

	@Override
	public String echoService(String input) {
		// TODO Auto-generated method stub
		try {
			String retval = eo.echo(input);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
