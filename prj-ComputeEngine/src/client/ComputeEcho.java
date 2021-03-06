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
			System.out.println("Uso ComputeEcho <host>");System.exit(1);
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
			stdOut.print("> "); stdOut.flush();
			while ( (input = stdIn.readLine())!=null) {
				EchoObject task = new EchoObject(input);
				comp.loadTask(task);
				output = (String) comp.execute();
				stdOut.println(output);
				stdOut.flush();
			}
		} catch (Exception e) {
			System.err.println("ComputeEcho exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
