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

package corba.robot;

//
// IDL:corba/robot/RobotSeguidorInt:1.0
//
final public class RobotSeguidorIntHolder implements org.omg.CORBA.portable.Streamable
{
    public RobotSeguidorInt value;

    public
    RobotSeguidorIntHolder()
    {
    }

    public
    RobotSeguidorIntHolder(RobotSeguidorInt initial)
    {
        value = initial;
    }

    public void
    _read(org.omg.CORBA.portable.InputStream in)
    {
        value = RobotSeguidorIntHelper.read(in);
    }

    public void
    _write(org.omg.CORBA.portable.OutputStream out)
    {
        RobotSeguidorIntHelper.write(out, value);
    }

    public org.omg.CORBA.TypeCode
    _type()
    {
        return RobotSeguidorIntHelper.type();
    }
}