/**
 * Eurocal_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.eurocal;

public class Eurocal_ServiceLocator extends org.apache.axis.client.Service implements org.example.www.eurocal.Eurocal_Service {

    public Eurocal_ServiceLocator() {
    }


    public Eurocal_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Eurocal_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for eurocalSOAP
    private java.lang.String eurocalSOAP_address = "http://localhost:8080/prj-soap/services/eurocalSOAP";

    public java.lang.String geteurocalSOAPAddress() {
        return eurocalSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String eurocalSOAPWSDDServiceName = "eurocalSOAP";

    public java.lang.String geteurocalSOAPWSDDServiceName() {
        return eurocalSOAPWSDDServiceName;
    }

    public void seteurocalSOAPWSDDServiceName(java.lang.String name) {
        eurocalSOAPWSDDServiceName = name;
    }

    public org.example.www.eurocal.Eurocal_PortType geteurocalSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(eurocalSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return geteurocalSOAP(endpoint);
    }

    public org.example.www.eurocal.Eurocal_PortType geteurocalSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.example.www.eurocal.EurocalSOAPStub _stub = new org.example.www.eurocal.EurocalSOAPStub(portAddress, this);
            _stub.setPortName(geteurocalSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void seteurocalSOAPEndpointAddress(java.lang.String address) {
        eurocalSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.example.www.eurocal.Eurocal_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.example.www.eurocal.EurocalSOAPStub _stub = new org.example.www.eurocal.EurocalSOAPStub(new java.net.URL(eurocalSOAP_address), this);
                _stub.setPortName(geteurocalSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("eurocalSOAP".equals(inputPortName)) {
            return geteurocalSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.example.org/eurocal/", "eurocal");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.example.org/eurocal/", "eurocalSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("eurocalSOAP".equals(portName)) {
            seteurocalSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
