package client;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import compute.Compute;

public class ComputePi {

	public static void main(String[] args) {
		if (args.length<2) {
			System.out.println("Uso ComputePi <host> <digits>");System.exit(1);
		}
		
		if(System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try {
			String name = "//" + args[0] + "/Compute";
			Compute comp = (Compute) Naming.lookup(name);
			Pi task = new Pi(Integer.parseInt(args[1]));
			BigDecimal pi = (BigDecimal) (comp.executeTask(task));
			System.out.println(pi);
		} catch (Exception e) {
			System.err.println("ComputePi exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
