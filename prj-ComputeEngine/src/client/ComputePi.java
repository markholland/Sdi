package client;

import java.rmi.RMISecurityManager;

public class ComputePi {

	public static void main(String[] args) {
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
