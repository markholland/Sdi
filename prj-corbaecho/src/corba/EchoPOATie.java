package corba;


/**
* corba/EchoPOATie.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Y:/Documents/workspace/prj-corbaecho/src/Echo.idl
* Wednesday, October 15, 2014 7:54:52 PM CEST
*/

public class EchoPOATie extends EchoPOA
{

  // Constructors

  public EchoPOATie ( corba.EchoOperations delegate ) {
      this._impl = delegate;
  }
  public EchoPOATie ( corba.EchoOperations delegate , org.omg.PortableServer.POA poa ) {
      this._impl = delegate;
      this._poa      = poa;
  }
  public corba.EchoOperations _delegate() {
      return this._impl;
  }
  public void _delegate (corba.EchoOperations delegate ) {
      this._impl = delegate;
  }
  public org.omg.PortableServer.POA _default_POA() {
      if(_poa != null) {
          return _poa;
      }
      else {
          return super._default_POA();
      }
  }
  public String echo (String x)
  {
    return _impl.echo(x);
  } // echo

  private corba.EchoOperations _impl;
  private org.omg.PortableServer.POA _poa;

} // class EchoPOATie