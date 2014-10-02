package client;

import compute.Task;

public class Pi implements Task {
	
	private int digits;
	
	public Pi(int digits) {
		this.digits = digits;
	}

	public Object execute() {
		return calculatePi(digits);
	}
	
	public static BigDecimal calculatePi(int digits) {
		
	}
}