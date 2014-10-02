package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import compute.Compute;

public class ComputeEcho {

	public static void main(String[] args) {
		if (args.length<1) {
			System.out.println("Uso echo <host>");System.exit(1);
		}
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter stdOut = new PrintWriter(System.out);
        String input,output;
		
		try {
			String name = "//" + args[0] + "/Compute";
			Compute comp = (Compute) Naming.lookup(name);
			while ( (input = stdIn.readLine())!=null) {
				EchoObject task = new EchoObject(input);
				output = (String) comp.executeTask(task);
				System.out.println(output);
			}
		} catch (Exception e) {
			System.err.println("ComputePi exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
