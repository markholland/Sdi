// **********************************************************************
//
// Generated by the ORBacus IDL to Java Translator
//
// Copyright (c) 2000
// Object Oriented Concepts, Inc.
// Billerica, MA, USA
//
// All Rights Reserved
//
// **********************************************************************

// Version: 4.0.3

package corba.camara;

//
// IDL:corba/camara/CamaraInt:1.0
//
final public class CamaraIntHolder implements org.omg.CORBA.portable.Streamable
{
    public CamaraInt value;

    public
    CamaraIntHolder()
    {
    }

    public
    CamaraIntHolder(CamaraInt initial)
    {
        value = initial;
    }

    public void
    _read(org.omg.CORBA.portable.InputStream in)
    {
        value = CamaraIntHelper.read(in);
    }

    public void
    _write(org.omg.CORBA.portable.OutputStream out)
    {
        CamaraIntHelper.write(out, value);
    }

    public org.omg.CORBA.TypeCode
    _type()
    {
        return CamaraIntHelper.type();
    }
}
