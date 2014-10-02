package client;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.EchoInt;

public class EchoRMI {

	public static void main(String[] args) {

		if (args.length<1) {
			System.out.println("Uso echo <host>");System.exit(1);
		}
		
		if(System.getSecurityManager()== null) 
		//Para usar esto hay que definir un fichero de política de seguridad.
			System.setSecurityManager(new RMISecurityManager());
		
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        PrintWriter stdOut = new PrintWriter(System.out);

	        String input,output;
	        try {
	          EchoInt obj = (EchoInt) Naming.lookup("echo");  // Obtain reference from name server

	          stdOut.print("> "); stdOut.flush();
	          while ( (input = stdIn.readLine())!=null) {
	        	  output = obj.echo(input);	//EJERCICIO: invocar el objeto RMI 
	              stdOut.println(output);
	              stdOut.print("> "); 
	              stdOut.flush();
	          }
	        }catch(Exception e) {
	              System.out.println("Error en el cliente de echo RMI : " + e.getMessage());
	        }
	}

}
