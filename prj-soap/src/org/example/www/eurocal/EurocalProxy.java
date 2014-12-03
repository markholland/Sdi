package org.example.www.eurocal;

public class EurocalProxy implements org.example.www.eurocal.Eurocal_PortType {
  private String _endpoint = null;
  private org.example.www.eurocal.Eurocal_PortType eurocal_PortType = null;
  
  public EurocalProxy() {
    _initEurocalProxy();
  }
  
  public EurocalProxy(String endpoint) {
    _endpoint = endpoint;
    _initEurocalProxy();
  }
  
  private void _initEurocalProxy() {
    try {
      eurocal_PortType = (new org.example.www.eurocal.Eurocal_ServiceLocator()).geteurocalSOAP();
      if (eurocal_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)eurocal_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)eurocal_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (eurocal_PortType != null)
      ((javax.xml.rpc.Stub)eurocal_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.example.www.eurocal.Eurocal_PortType getEurocal_PortType() {
    if (eurocal_PortType == null)
      _initEurocalProxy();
    return eurocal_PortType;
  }
  
  public double convertirAEuros(java.lang.String codi, double moneda) throws java.rmi.RemoteException{
    if (eurocal_PortType == null)
      _initEurocalProxy();
    return eurocal_PortType.convertirAEuros(codi, moneda);
  }
  
  public double convertirEuros(java.lang.String codi, double euros) throws java.rmi.RemoteException{
    if (eurocal_PortType == null)
      _initEurocalProxy();
    return eurocal_PortType.convertirEuros(codi, euros);
  }
  
  
}