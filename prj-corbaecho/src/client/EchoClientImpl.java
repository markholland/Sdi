package client;
/*
 * The client implementation is generated by the ORB Studio.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Properties;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

class EchoClientImpl {
	private corba.Echo target = null;
	private org.omg.CORBA.ORB orb = null;

	/**
	 * Constructor for EchoClientImpl
	 * 
	 * @throws IOException
	 */
	public EchoClientImpl() throws IOException {
		initORB(null);
	}

	/**
	 * Constructor for EchoClientImpl
	 * 
	 * @throws IOException
	 * @see java.lang.Object#Object()
	 */
	public EchoClientImpl(String[] args) throws IOException {
		initORB(args);
	}

	/**
	 * Initialize ORB.
	 *  
	 * @param args
	 * @throws IOException
	 */
	public void initORB(String[] args) throws IOException {

		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "com.sun.corba.se.internal.POA.POAORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "com.sun.corba.se.internal.corba.ORBSingleton");
		//props.setProperty("org.omg.CORBA.ORBClass", "com.ooc.CORBA.ORB");
		//props.setProperty("org.omg.CORBA.ORBSingletonClass", "com.ooc.CORBA.ORBSingleton");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		props.put("org.omg.CORBA.ORBInitialPort", "1050");

		// Initialize the ORB
		orb = org.omg.CORBA.ORB.init((String[])args, props);

		// ---- Uncomment below to enable Naming Service access. ----
		 org.omg.CORBA.Object ncobj;
		try {
			ncobj = orb.resolve_initial_references("NameService");
			NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			org.omg.CORBA.Object obj;
			try {
				obj = nc.resolve_str("EchoObject");
				target = corba.EchoHelper.narrow(obj);		
			} catch (NotFound e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CannotProceed e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InvalidName e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 

	    //LineNumberReader input = new LineNumberReader(new FileReader("server.ior"));
		//String ior = input.readLine();
		//org.omg.CORBA.Object obj = orb.string_to_object(ior);

		//target = corba.EchoHelper.narrow(obj);
	}

	/**
	 * Obtain ORB Interface.
	 * 
	 * @return
	 */
	public corba.Echo getORBInterface() {
		return target;
	}

	/**
	 * Shutdown ORB.
	 */
	public void shutdown() {
		orb.shutdown(true);
	}

	/**
	 * Test driver for EchoClientImpl.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EchoClientImpl test = new EchoClientImpl(); // Might need to pass args

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        PrintWriter stdOut = new PrintWriter(System.out);
	        String input,output;
			try {
				stdOut.print("> "); stdOut.flush();
				while ( (input = stdIn.readLine())!=null) {
					output = test.getORBInterface().echo(input);
					stdOut.println(output);
					stdOut.flush();
				}
				
				test.shutdown();
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
			test.shutdown();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
