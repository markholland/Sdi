package client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import compute.Task;

public class EchoObject implements Task {
	
	String myURL="localhost";
	String input;

	public EchoObject(String input){
		this.input = input;
		try {
			myURL=InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			myURL="localhost";
		}
	}
	
	
	public Object execute() {
		return echo(input);
	}

	public String echo(String input) {
		Date h = new Date();
		String fecha = DateFormat.getTimeInstance(3,Locale.FRANCE).format(h);

		String ret = myURL + ":" + fecha + "> " +  input;
		try {
			Thread.sleep(3000);  ret = ret + " (retrasada 3 segundos)";
		} catch (InterruptedException e) {}

		return ret;
	}
}
