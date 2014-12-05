/*package camaraR;
/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 
public class CamaraIntServerImpl extends corba.camara.CamaraIntPOA {
	/**
	 * Constructor for CamaraIntServerImpl 
	 
	public CamaraIntServerImpl() {
	}
}
*/

package camaraR;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import comm.DifusionMulticast;
import camaraR.CamaraIntServerImpl;
import corba.camara.IPYPortD;
import corba.camara.ListaSuscripcionD;
import corba.camara.suscripcionD;
import corba.instantanea.EstadoRobotD;
import corba.instantanea.InstantaneaD;
import corba.khepera.escenario.EscenarioD;
/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 */
public class CamaraIntServerImpl extends corba.camara.CamaraIntPOA {
	/**
	 * Constructor for CamaraIntServerImpl 
	 */
	private IPYPortD ipyport;
	private EscenarioD escenario;
	private org.omg.PortableServer.POA poa_;
	private org.omg.CORBA.ORB orb_;
	private List<String> listaConsola = Collections.synchronizedList(new LinkedList<String>());
	private List<String> listaRobots = new LinkedList<String>();
	private LinkedList<EstadoRobotD> listaEstados = new LinkedList<EstadoRobotD>();
	private InstantaneaD instantanea;
	private int nrobots;
	private int ncons;

	
	CamaraIntServerImpl(org.omg.CORBA.ORB orb, org.omg.PortableServer.POA poa,IPYPortD ipyportinicial, EscenarioD escenarioinicial) 
    {
        orb_ = orb;
        poa_ = poa;
        this.ipyport = ipyportinicial;
		this.escenario = escenarioinicial;
        nrobots = 0;
        ncons=0;
    }
	
//	 	suscripcionD SuscribirRobot(in string IORrob);	
	@Override
	public synchronized suscripcionD SuscribirRobot(String IORrob){
		System.out.println("Robot "+ nrobots +" : Registrado!");
		//org.omg.CORBA.Object obj = orb_.string_to_object(IORrob);
     	//RobotSeguidorInt rob = corba.robot.RobotSeguidorIntHelper.narrow(obj);
		if(!listaRobots.contains(IORrob)){
			listaRobots.add(IORrob);
		}else{
			
			System.out.println("YA REGISTRADO!");
		}
     	
     	//corba.camara.suscripcionD sus = new corba.camara.suscripcionD();
     	//sus.id = nrobots++;
     	//sus.iport = this.ipyport;
		suscripcionD susD = new suscripcionD(nrobots++, ipyport, escenario);
		return susD;
	}
		//suscripcionD SuscribirConsola(in string IORcons);	
	@Override
	public synchronized suscripcionD SuscribirConsola(String IORcons){
		System.out.println("Camara "+ ncons +" : Registrada!");
		//org.omg.CORBA.Object obj = orb_.string_to_object(IORcons);
     	//ConsolaInt con = corba.consola.ConsolaIntHelper.narrow(obj);
     	listaConsola.add(IORcons);
		suscripcionD susD = new suscripcionD(ncons++, ipyport, escenario);
		return susD;
	}
	
	@Override
	public synchronized void BajaConsola(String IORcons) {
     	listaConsola.remove(IORcons);
	}
	
	@Override
	public synchronized void BajaRobot(String IORrob) {
     	listaRobots.remove(IORrob);
	}
	
	@Override
	public synchronized void ModificarEscenario(EscenarioD otroescenario) {
		escenario=otroescenario;
	}
	
	@Override
	public EscenarioD ObtenerEscenario() {
		return escenario;
	}
	
	@Override
	public IPYPortD ObtenerIPYPortDifusion() {
		return ipyport;
	}
	
	@Override
	public InstantaneaD ObtenerInstantanea() {
		
		InstantaneaD insd = new InstantaneaD((EstadoRobotD[]) listaEstados.toArray(new EstadoRobotD[0]));
		return insd;
	}
	public void start(){
		CamaraDifusion camarad = new CamaraDifusion(this);
		camarad.start();
    }
	
	@Override
	public ListaSuscripcionD ObtenerLista() {
		ListaSuscripcionD lsd = new ListaSuscripcionD((String[])listaRobots.toArray(new String[0]),(String[])listaConsola.toArray(new String[0]));
		return lsd;
	}
	class CamaraDifusion extends Thread{
	     private DifusionMulticast difusion;
	     private CamaraIntServerImpl parent;
	     
	      //------------------------------------------------------------------------------
	      public CamaraDifusion(CamaraIntServerImpl camaraIntServerImpl){
	         difusion = new DifusionMulticast(ipyport);
	         parent = camaraIntServerImpl;
	      }

	      //------------------------------------------------------------------------------
	      public CamaraDifusion(IPYPortD ipyport,CamaraIntServerImpl camaraIntServerImpl) {
	    	  difusion = new DifusionMulticast(ipyport);
	    	  parent = camaraIntServerImpl;
	    	  // TODO Auto-generated constructor stub
		}

		@Override
		public void run(){
	        corba.instantanea.EstadoRobotDHolder st = new corba.instantanea.EstadoRobotDHolder(); //new corba.instantanea.EstadoRobotDHolder();
	        corba.instantanea.EstadoRobotD erd = null;
	        corba.robot.RobotSeguidorInt rob = null;
	        String ior=null;
	        LinkedList<corba.robot.RobotSeguidorInt> listaFallos = new LinkedList<corba.robot.RobotSeguidorInt>();
	        
	         while(true){
	           listaEstados.clear();
	           listaFallos.clear();
	           synchronized(parent){
	           for (Iterator i = listaRobots.iterator(); i.hasNext(); ){
	             try {
	            	 ior =(String) i.next();
	            	 org.omg.CORBA.Object obj = orb_.string_to_object(ior);
	            	 rob  = corba.robot.RobotSeguidorIntHelper.narrow(obj);
	            
	            	 if(rob==null){
	            		 System.out.println("ROB VACIO!");
	            	 }
	            	 rob.ObtenerEstado(st);
	    
	            	 listaEstados.add(st.value);
	            	 
	                //EJERCICIO: invocar via CORBA el metodo ObtenerEstado y anyadir
	               //el estado del robot correspondiente a la lista de estados          

	             } catch ( org.omg.CORBA.COMM_FAILURE e){
	                 System.out.println("Detectado fallo Robot: en " + i );
	                 listaFallos.add(rob);
	                 i.remove();
	               //EJERCICIO: anyadir el robot caido a la lista de fallos 
	            } 
	          }
	           if(!listaEstados.isEmpty()){
	        	   System.out.println(listaEstados.size());
	        	   instantanea = new InstantaneaD();
	        	   instantanea.estadorobs=listaEstados.toArray(new EstadoRobotD[0]);
	        	   //instantanea = new InstantaneaD((EstadoRobotD[]) listaEstados.toArray());
	        	   difusion.sendObject(instantanea);
	           }
	         }//END-Synchronized

	           //EJERCICIO: crear una instantanea a partir de la lista de estados de los robots. 
	           
	           
	           //EJERCICIO: difundir la instantanea 
	           
	           try{
	               Thread.sleep(400);
	           }catch(InterruptedException e){
	               e.printStackTrace();
	           }
	        }
	      }
	    }
}
