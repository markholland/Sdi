package server;
/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 */
public class EchoServerImpl extends corba.EchoPOA {
	/**
	 * Constructor for EchoServerImpl 
	 */
	public EchoServerImpl() {
	}
	
	@Override
	public String echo_(String x) {
		EchoObject eo = new EchoObject();
		return eo.echo(x);
	}

}
