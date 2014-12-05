package robotR;
import java.util.Properties;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.IdAssignmentPolicyValue;
// import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.ThreadPolicyValue;

import corba.camara.CamaraInt;
import corba.khepera.robot.PosicionD;

public class Server_AOM {

	public static void main(String[] args) {

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

			// Create myPOA with the right policies
			POA poa = poaRoot.create_POA("RobotSeguidorIntServerImpl_poa",	poaRoot.the_POAManager(), policies);
						
			// Activate the servant with the ID on myPOA
			byte[] objectId = "AnyObjectID".getBytes();
			
			// Activate the POA manager
			poaRoot.the_POAManager().activate();


			//Find the camera in the naming service
			CamaraInt camara=null;
			org.omg.CORBA.Object ncobj = null;
        	org.omg.CORBA.Object deobj = null;
    		try {
    			ncobj = orb.resolve_initial_references("NameService");
    			NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
    			deobj = nc.resolve_str("Camara");
    			camara = corba.camara.CamaraIntHelper.narrow(deobj);
    		} catch (InvalidName e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			// ---- Uncomment below to enable Naming Service access. ----
			//org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
			//NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			//nc.bind(nc.to_name("MyServerObject"), obj);
    		
    		// Create the servant
    		System.out.println("Soy " + args[1] + ", tengo id = " + args[0]);
    		RobotSeguidorIntServerImpl servant = new RobotSeguidorIntServerImpl(poa,orb,Integer.valueOf(args[0]).intValue(),args[1],-1,camara,new PosicionD(0,0),new PosicionD(200, 200));
    		// Get a reference to the servant and write it down.
    		
			poa.activate_object_with_id(objectId, servant);
			obj = poa.servant_to_reference(servant);
			servant.miIOR=orb.object_to_string(servant._this());
			
			System.out.println("Robot ready...");
			servant.start();
			// Wait for incoming requests
			orb.run();
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
