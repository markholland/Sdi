/**
 * Eurocal_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.eurocal;

public interface Eurocal_Service extends javax.xml.rpc.Service {
    public java.lang.String geteurocalSOAPAddress();

    public org.example.www.eurocal.Eurocal_PortType geteurocalSOAP() throws javax.xml.rpc.ServiceException;

    public org.example.www.eurocal.Eurocal_PortType geteurocalSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
