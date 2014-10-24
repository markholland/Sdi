package robot;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.IdAssignmentPolicyValue;
// import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.ThreadPolicyValue;

import corba.camara.CamaraInt;

public class Server_AOM {
	
	static CamaraInt camara;
	static int ok=0;

	public static void main(String[] args) {

		Properties props = System.getProperties();
		props.setProperty("org.omg.CORBA.ORBClass", "com.sun.corba.se.internal.POA.POAORB");
		props.setProperty("org.omg.CORBA.ORBSingletonClass", "com.sun.corba.se.internal.corba.ORBSingleton");
		// Solo si se cambia el host 
		//props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		// Solo si se cambia el port 
		//props.put("org.omg.CORBA.ORBInitialPort", "1050");
		//props.put("ooc.orb.service.NameService", "corbaloc::localhost:1111/NameService");

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

			// Create the servant
			RobotSeguidorIntServerImpl servant = new RobotSeguidorIntServerImpl();

			// Activate the servant with the ID on myPOA
			byte[] objectId = "AnyObjectID".getBytes();
			poa.activate_object_with_id(objectId, servant);
			
			// Activate the POA manager
			poaRoot.the_POAManager().activate();

			// Get a reference to the servant and write it down.
			obj = poa.servant_to_reference(servant);

			// ---- Uncomment below to enable Naming Service access. ----
			// org.omg.CORBA.Object ncobj = orb.resolve_initial_references("NameService");
			// NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
			// nc.bind(nc.to_name("MyServerObject"), obj);

			//PrintWriter ps = new PrintWriter(new FileOutputStream(new File("server.ior")));
			//ps.println(orb.object_to_string(obj));
			//ps.close();
			
			/*MODIFICADO*/			
			do{
		        try{
		        	//EJERCICIO:Conectar con el servidor de nombre y obtener una referencia 
		        	//a la **camara** 
		        	org.omg.CORBA.Object ncobj=null;
		    		try {
		    			ncobj = orb.resolve_initial_references("NameService");
		    		} catch (InvalidName e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    		NamingContextExt nc = NamingContextExtHelper.narrow(ncobj);
		    		org.omg.CORBA.Object obje=null;
		    		try {
		    			obje = nc.resolve_str("Camara");
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
		    		camara = corba.camara.CamaraIntHelper.narrow(obje);
		        	

		          System.out.println("Identificador: " + servant);
			         //EJERCICIO: convertir la referencia al robot en un IOR en formato String 
	              servant.miIOR = orb.object_to_string(obj); 

		          servant.orb = orb;
		          servant.camara = camara;
		          if (args.length>0) servant.minombre = args[0]; else servant.minombre="Robot";
		          ok=1;
		        } catch(Exception ex) {
		          System.out.println("El robot no se registro bien en la camara. Reintentando...");
		        }
		      } while(ok==0);

		      servant.start();
			  /*FIN MODIFICADO*/
	

			System.out.println("Robot Server ready...");

			// Wait for incoming requests
			orb.run();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
