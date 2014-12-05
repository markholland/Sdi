package services;

public class CifradorProxy implements services.Cifrador {
  private String _endpoint = null;
  private services.Cifrador cifrador = null;
  
  public CifradorProxy() {
    _initCifradorProxy();
  }
  
  public CifradorProxy(String endpoint) {
    _endpoint = endpoint;
    _initCifradorProxy();
  }
  
  private void _initCifradorProxy() {
    try {
      cifrador = (new services.CifradorServiceLocator()).getCifrador();
      if (cifrador != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)cifrador)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)cifrador)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (cifrador != null)
      ((javax.xml.rpc.Stub)cifrador)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public services.Cifrador getCifrador() {
    if (cifrador == null)
      _initCifradorProxy();
    return cifrador;
  }
  
  public java.lang.String cifra(java.lang.String input, java.lang.String clave) throws java.rmi.RemoteException{
    if (cifrador == null)
      _initCifradorProxy();
    return cifrador.cifra(input, clave);
  }
  
  public java.lang.String descifra(java.lang.String input, java.lang.String clave) throws java.rmi.RemoteException{
    if (cifrador == null)
      _initCifradorProxy();
    return cifrador.descifra(input, clave);
  }
  
  
}