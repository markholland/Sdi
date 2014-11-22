/**
 * CifradorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package services;

public class CifradorServiceLocator extends org.apache.axis.client.Service implements services.CifradorService {

    public CifradorServiceLocator() {
    }


    public CifradorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CifradorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Cifrador
    private java.lang.String Cifrador_address = "http://localhost:8080/prj-soapcif/services/Cifrador";

    public java.lang.String getCifradorAddress() {
        return Cifrador_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CifradorWSDDServiceName = "Cifrador";

    public java.lang.String getCifradorWSDDServiceName() {
        return CifradorWSDDServiceName;
    }

    public void setCifradorWSDDServiceName(java.lang.String name) {
        CifradorWSDDServiceName = name;
    }

    public services.Cifrador getCifrador() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Cifrador_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCifrador(endpoint);
    }

    public services.Cifrador getCifrador(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            services.CifradorSoapBindingStub _stub = new services.CifradorSoapBindingStub(portAddress, this);
            _stub.setPortName(getCifradorWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCifradorEndpointAddress(java.lang.String address) {
        Cifrador_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (services.Cifrador.class.isAssignableFrom(serviceEndpointInterface)) {
                services.CifradorSoapBindingStub _stub = new services.CifradorSoapBindingStub(new java.net.URL(Cifrador_address), this);
                _stub.setPortName(getCifradorWSDDServiceName());
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
        if ("Cifrador".equals(inputPortName)) {
            return getCifrador();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://services", "CifradorService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://services", "Cifrador"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Cifrador".equals(portName)) {
            setCifradorEndpointAddress(address);
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
