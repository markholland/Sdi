package client;

import java.net.*;
import java.io.*;
import rmi.*;

public class EchoObjectStub implements EchoInt {

  private Socket echoSocket = null;
  private PrintWriter os = null;
  private BufferedReader is = null;
  private String host = "localhost";
  private int port=7;
  private String output = "Error";
  private boolean connected  = false;

  public void setHostAndPort(String host, int port) {
    this.host= host; this.port =port;
  }

  public String echo(String input)throws java.rmi.RemoteException {
    connect();
    if (echoSocket != null && os != null && is != null) {
  	try {
             os.println(input);
             os.flush();
             output= is.readLine();
      } catch (IOException e) {
        System.err.println("I/O failed in reading/writing socket");
      }
    }
    disconnect();
    return output;
  }

  private synchronized void connect() throws java.rmi.RemoteException {
    try{
      if (!connected) {
       echoSocket = new Socket(host,port);
       is = new BufferedReader( new InputStreamReader(
       echoSocket.getInputStream()));
       os = new PrintWriter(echoSocket.getOutputStream());
       System.out.println("Connected to server");
      }
    } catch (Exception e) {
       throw new java.rmi.RemoteException();
    }
  }

  private synchronized void disconnect(){
    try {
          os.close();
          is.close();
          echoSocket.close();
          connected = false;
          System.out.println("Disconnected from server");
    } catch (IOException e) {
      System.err.println("Error closing socket");
    }
  }
}
