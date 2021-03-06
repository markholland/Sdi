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
public abstract class RobotSeguidorIntPOA
    extends org.omg.PortableServer.Servant
    implements org.omg.CORBA.portable.InvokeHandler,
               RobotSeguidorIntOperations
{
    static final String[] _ob_ids_ =
    {
        "IDL:corba/robot/RobotSeguidorInt:1.0",
    };

    public RobotSeguidorInt
    _this()
    {
        return RobotSeguidorIntHelper.narrow(super._this_object());
    }

    public RobotSeguidorInt
    _this(org.omg.CORBA.ORB orb)
    {
        return RobotSeguidorIntHelper.narrow(super._this_object(orb));
    }

    public String[]
    _all_interfaces(org.omg.PortableServer.POA poa, byte[] objectId)
    {
        return _ob_ids_;
    }

    public org.omg.CORBA.portable.OutputStream
    _invoke(String opName,
            org.omg.CORBA.portable.InputStream in,
            org.omg.CORBA.portable.ResponseHandler handler)
    {
        final String[] _ob_names =
        {
            "ObtenerEstado"
        };

        int _ob_left = 0;
        int _ob_right = _ob_names.length;
        int _ob_index = -1;

        while(_ob_left < _ob_right)
        {
            int _ob_m = (_ob_left + _ob_right) / 2;
            int _ob_res = _ob_names[_ob_m].compareTo(opName);
            if(_ob_res == 0)
            {
                _ob_index = _ob_m;
                break;
            }
            else if(_ob_res > 0)
                _ob_right = _ob_m;
            else
                _ob_left = _ob_m + 1;
        }

        switch(_ob_index)
        {
        case 0: // ObtenerEstado
            return _OB_op_ObtenerEstado(in, handler);
        }

        throw new org.omg.CORBA.BAD_OPERATION();
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_ObtenerEstado(org.omg.CORBA.portable.InputStream in,
                         org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        corba.instantanea.EstadoRobotDHolder _ob_ah0 = new corba.instantanea.EstadoRobotDHolder();
        ObtenerEstado(_ob_ah0);
        out = handler.createReply();
        corba.instantanea.EstadoRobotDHelper.write(out, _ob_ah0.value);
        return out;
    }
}
