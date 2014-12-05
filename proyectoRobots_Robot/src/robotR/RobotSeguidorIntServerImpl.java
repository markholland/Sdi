package robotR;

import java.io.FileNotFoundException;

import khepera.control.Destino;
import khepera.control.Trayectoria;
import khepera.escenario.Escenario;
import khepera.robot.IzqDer;
import khepera.robot.Polares;
import khepera.robot.RobotKhepera;
import comm.DifusionMulticast;

import corba.camara.CamaraInt;
import corba.instantanea.EstadoRobotD;
import corba.instantanea.EstadoRobotDHolder;
import corba.instantanea.InstantaneaD;
import corba.instantanea.PuntosRobotD;
import corba.khepera.escenario.EscenarioD;
import corba.khepera.robot.PosicionD;

/**
 * This class is the implemetation object for your IDL interface.
 *
 * Let the Eclipse complete operations code by choosing 'Add unimplemented methods'.
 */
public class RobotSeguidorIntServerImpl extends corba.robot.RobotSeguidorIntPOA {
	private org.omg.CORBA.ORB orb;
    private CamaraInt camara; 			//Camara cenital
    private String minombre;			//Nombre robot
    private int miid;					//Id robot
    String miIOR;						//IOR robbot
    private EstadoRobotD erd;
    private int lider;
    private corba.khepera.robot.PosicionD posicion;
    private corba.khepera.robot.PosicionD objetivo;
    private Destino dst;
    private EscenarioD escenario;
    private InstantaneaD instantanea;
    private org.omg.PortableServer.POA poa;
    private RobotKhepera r;
    private double rand;
    
	public RobotSeguidorIntServerImpl(org.omg.PortableServer.POA poa, org.omg.CORBA.ORB orb, int miid, String minombre, int milider, CamaraInt camara, PosicionD posicion, PosicionD objetivo ) throws FileNotFoundException {
		this.miid=miid;
		//this.miIOR=orb.object_to_string(this._this());
		this.minombre=minombre;
		this.lider=milider;
		this.orb = orb;
		this.poa = poa;
		this.camara = camara;
		this.posicion=posicion;
		this.objetivo=objetivo;
		this.dst=new Destino();
		this.rand=Math.random();
		try {
			r=new RobotKhepera(new PosicionD(0,0), new Escenario("dibujo"), 0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public RobotSeguidorIntServerImpl() {
		
	}

	@Override
	public void ModificarEscenario(EscenarioD nuevoescenario) {
		escenario=nuevoescenario;		
	}

	@Override
	public void ModificarLider(int nuevolider) {
		lider=nuevolider;
	}

	public void start(){
        new RobotDifusion().start();
    }
	@Override
	public void ModificarObjetivo(PosicionD nuevoobjetivo) {
		System.out.println("Modificando objetivo");
		objetivo=nuevoobjetivo;
	}

	@Override
	public void ModificarPosicion(PosicionD nuevaposicion) {
		System.out.println("Modificando posicion");
		posicion=nuevaposicion;
		r.fijarPosicion(nuevaposicion);
		
	}

	@Override
	public synchronized void ObtenerEstado(EstadoRobotDHolder erdh) {
		erd=new EstadoRobotD();
		erd.id=miid;
		erd.IORrob=miIOR;
		erd.nombre=minombre;
		erd.idLider=lider;
		
		erd.posObj=objetivo;
		PuntosRobotD robop =r.posicionRobot();
		erd.puntrob=r.posicionRobot();
		erd.refrob=_this();
		erdh.value=erd;
		return;
		
		//erdh.value=erd;
		
		
	}
	class RobotDifusion extends Thread{

	      private DifusionMulticast difusion;
	      private EstadoRobotD sr;
	      private corba.camara.suscripcionD sus;
	      @Override
		public void run(){
	    	  System.out.println("Started Robot!");
	    	  sus = camara.SuscribirRobot(miIOR);
	    	  difusion = new DifusionMulticast(sus.iport);  
	          miid=sus.id;
	        while(true){
	        	instantanea = (InstantaneaD) difusion.receiveObject();
	        	//System.out.println(instantanea.estadorobs.toString());
	        	for (int i = 0 ; i<instantanea.estadorobs.length; i++ ){
	        		sr = instantanea.estadorobs[i];
		            System.out.println("Robot " + sr.id + " : " + sr.nombre);
	        	};
	        	Polares pol = r.posicionPolares();
	        	Trayectoria tr = new Trayectoria(pol, objetivo);
	        	System.out.println("angulo,ux,uy,x,y"+pol.angulo+","+pol.ux+","+pol.ux+","+pol.x+","+pol.y);
	        	IzqDer velocidad = dst.calcularVelocidad(tr);
	        	System.out.println(velocidad.der+","+velocidad.izq+",der,izq");
	        	r.fijarVelocidad(velocidad.izq, velocidad.der);
	        	r.avanzar();
	        	//System.out.println(instantanea.estadorobs.toString());
	          try{
	            Thread.sleep(400);
	          }catch(InterruptedException e){
	            e.printStackTrace();
	          }
	        }
	      }
	    }
}
