package client;

import java.net.*;
import java.io.*;
import rmi.EchoInt;

public class EchoObjectStub2 implements EchoInt, Runnable {

  private Socket echoSocket = null;
  private PrintWriter os = null;
  private BufferedReader is = null;
  private String host = "localhost";
  private int port=7;
  private String output = "Error";
  private boolean connected  = false;

  private Thread reloj = new Thread(this, "reloj");
  private int timeout = 50;
  private boolean firstTime = true;

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
    programDisconnection();
    return output;
  }

  private synchronized void connect() throws java.rmi.RemoteException {
    try{
      if (!connected) {
       echoSocket = new Socket(host,port);
       is = new BufferedReader( new InputStreamReader(
       echoSocket.getInputStream()));
       os = new PrintWriter(echoSocket.getOutputStream());
       connected = true;
       System.out.println("Connected to server");
      } else
       reloj.suspend();
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

  private synchronized void programDisconnection(){
    timeout=50;
    if (firstTime) {
         reloj.start(); firstTime=false;
    } else reloj.resume();
  }

  public void run(){
    while (true) {
      while (timeout>0){
         timeout--;
         try{
           Thread.sleep(50);
           //System.out.println("timeout: " + timeout);
         }catch(InterruptedException e){}
      }
      disconnect();
      reloj.suspend();
    }
  }

}
