/**
 * Cifrador.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public interface Cifrador extends java.rmi.Remote {
    public java.lang.String cifra(java.lang.String input, java.lang.String clave) throws java.rmi.RemoteException;
    public java.lang.String descifra(java.lang.String input, java.lang.String clave) throws java.rmi.RemoteException;
}
