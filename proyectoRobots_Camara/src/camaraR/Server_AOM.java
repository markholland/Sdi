package camaraR;
import java.util.Properties;

import khepera.escenario.Escenario;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.IdAssignmentPolicyValue;
// import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.ThreadPolicyValue;

import corba.camara.IPYPortD;

public class Server_AOM {

	public static void main(String[] args) {
		IPYPortD ipyport;
		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "com.sun.corba.se.internal.POA.POAORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "com.sun.corba.se.internal.corba.ORBSingleton");
		props.put("org.omg.CORBA.ORBInitialPort", "1050");
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		try {
			// Initialize the ORB.
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, props);

			// get a reference to the root POA
			org.omg.CORBA.Object obj = orb.resolve_initial_references("RootPOA");
			POA poaRoot = POAHelper.narrow(obj);

			// Create policies for our persistent POA
			org.omg.CORBA.Policy[] policies = {
					// poaRoot.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
					poaRoot.create_id_assignment_policy(IdAssignmentPolicyValue.USER_ID),
					poaRoot.create_thread_policy(ThreadPolicyValue.ORB_CTRL_MODEL) 
			};
			if (args.length==4)
				ipyport = new IPYPortD( args[2], Integer.parseInt(args[3]) );
			else
				ipyport = new IPYPortD( "228.2.3.4", 7000);
				System.out.println("Difusion por canal. " + ipyport.ip + " / " + ipyport.port);
			
			// Create myPOA with the right policies
			POA poa = poaRoot.create_POA("CamaraIntServerImpl_poa",	poaRoot.the_POAManager(), policies);

			// Create the servant
			Escenario escenario = new Escenario("dibujo");
			
			CamaraIntServerImpl servant = new CamaraIntServerImpl(orb,poa,ipyport, escenario.toEscenarioD());

			// Activate the servant with the ID on myPOA
			byte[] objectId = "AnyObjectID".getBytes();
			poa.activate_object_with_id(objectId, servant);
			
			
			// Activate the POA manager
			poaRoot.the_POAManager().activate();

			// Get a reference to the servant and write it down.
			obj = poa.servant_to_reference(servant);

			// ---- Uncomment below to enable Naming Service access. ----
			org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
			NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			nc.rebind(nc.to_name("Camara"), obj);

			System.out.println("Camara registrada...");

			// Wait for incoming requests
			servant.start();
			orb.run();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
