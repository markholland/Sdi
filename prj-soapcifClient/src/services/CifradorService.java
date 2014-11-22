/**
 * CifradorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public interface CifradorService extends javax.xml.rpc.Service {
    public java.lang.String getCifradorAddress();

    public services.Cifrador getCifrador() throws javax.xml.rpc.ServiceException;

    public services.Cifrador getCifrador(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
