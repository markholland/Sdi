/**
 * Eurocal_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.eurocal;

public interface Eurocal_PortType extends java.rmi.Remote {
    public double convertirAEuros(java.lang.String codi, double moneda) throws java.rmi.RemoteException;
    public double convertirEuros(java.lang.String codi, double euros) throws java.rmi.RemoteException;
}
