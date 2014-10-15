package server;
/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 */
public class CifradorServerImpl extends corba.CifradorPOA {
	/**
	 * Constructor for CifradorServerImpl 
	 */
	CifradorObject cifrador = new CifradorObject();
	
	public CifradorServerImpl() {
	}

	@Override
	public String cifra(String input, String clave) {
		return cifrador.cifra(input, clave);
	}

	@Override
	public String descifra(String input, String clave) {
		return cifrador.descifra(input, clave);
	}
}
