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

package corba;

//
// IDL:corba/Echo:1.0
//
public class _EchoStub extends org.omg.CORBA.portable.ObjectImpl
                       implements Echo
{
    private static final String[] _ob_ids_ =
    {
        "IDL:corba/Echo:1.0",
    };

    public String[]
    _ids()
    {
        return _ob_ids_;
    }

    final public static java.lang.Class _ob_opsClass = EchoOperations.class;

    //
    // IDL:corba/Echo/echo_:1.0
    //
    public String
    echo_(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("echo_", true);
                    out.write_string(_ob_a0);
                    in = _invoke(out);
                    String _ob_r = in.read_string();
                    return _ob_r;
                }
                catch(org.omg.CORBA.portable.RemarshalException _ob_ex)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _ob_aex)
                {
                    final String _ob_id = _ob_aex.getId();
                    in = _ob_aex.getInputStream();

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _ob_id);
                }
                finally
                {
                    _releaseReply(in);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("echo_", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                EchoOperations _ob_self = (EchoOperations)_ob_so.servant;
                try
                {
                    return _ob_self.echo_(_ob_a0);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }
}