package comm;

import java.io.*;
import java.net.*;
import corba.camara.IPYPortD;

public class Difusion{

  MulticastSocket socket;
  corba.camara.IPYPortD ipyport;
  public InetAddress group;

//------------------------------------------------------------------------------
  public  Difusion(IPYPortD ipyport){
    this.ipyport = ipyport;
    
    String multicastAddress = ipyport.ip;
    int multicastPort = ipyport.port;

  //EJERCICIO: 
  //Crear el socket multicast 
    try {
		socket = new MulticastSocket(multicastPort);
	} catch (IOException e1) {
		e1.printStackTrace();
		return;
	}
  //EJERCICIO: 
  //Obtener la direccion del grupo 
    try {
		group = InetAddress.getByName(multicastAddress);
	} catch (UnknownHostException e2) {
		e2.printStackTrace();
		return;
	}
  //EJERCICIO: 
  //Unirse al grupo 
    try {
		socket.joinGroup(group);
	} catch (IOException e) {
		e.printStackTrace();
		return;
	}
    
  }

//------------------------------------------------------------------------------
  public Object receiveObject(){

    Object object = null;
    ObjectInputStream ois = null;
    byte[] buffer;
    DatagramPacket packet;
    ByteArrayInputStream bis;


  //EJERCICIO: recibir el paquete y deserializarlo 
    buffer = new byte[4096];
    packet = new DatagramPacket(buffer,buffer.length);
    try {
		socket.receive (packet);
	} catch (IOException e) {
		e.printStackTrace();
	}
    buffer = packet.getData ();
    bis = new ByteArrayInputStream(buffer);
    try {
		ois = new ObjectInputStream(bis);
	} catch (IOException e) {
		e.printStackTrace();
	}
    try {
		object = (Object) ois.readObject ();
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}

    return object;
  }

//------------------------------------------------------------------------------
  public void sendObject(Object object){

    ByteArrayOutputStream bos;
    ObjectOutputStream oos = null;
    byte[] buffer;
    DatagramPacket packet;

  //EJERCICIO: serializar el paquete y difundirlo
    bos = new ByteArrayOutputStream();
    try {
		oos = new ObjectOutputStream(bos);
	} catch (IOException e) {
		e.printStackTrace();
	}
    try {
		oos.writeObject (object);
	} catch (IOException e) {
		e.printStackTrace();
	}
    buffer = bos.toByteArray();
    
    packet = new DatagramPacket(buffer,buffer.length,group,ipyport.port);
    try {
		socket.send(packet);
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
}