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
// IDL:corba/camara/suscripcionD:1.0
//
final public class suscripcionDHolder implements org.omg.CORBA.portable.Streamable
{
    public suscripcionD value;

    public
    suscripcionDHolder()
    {
    }

    public
    suscripcionDHolder(suscripcionD initial)
    {
        value = initial;
    }

    public void
    _read(org.omg.CORBA.portable.InputStream in)
    {
        value = suscripcionDHelper.read(in);
    }

    public void
    _write(org.omg.CORBA.portable.OutputStream out)
    {
        suscripcionDHelper.write(out, value);
    }

    public org.omg.CORBA.TypeCode
    _type()
    {
        return suscripcionDHelper.type();
    }
}
