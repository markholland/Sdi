package corba;

/**
* corba/EchoHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Y:/Documents/workspace/prj-corbaecho/src/Echo.idl
* Wednesday, October 15, 2014 7:54:52 PM CEST
*/

public final class EchoHolder implements org.omg.CORBA.portable.Streamable
{
  public corba.Echo value = null;

  public EchoHolder ()
  {
  }

  public EchoHolder (corba.Echo initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = corba.EchoHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    corba.EchoHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return corba.EchoHelper.type ();
  }

}
