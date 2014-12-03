/**
 * EurocalSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.eurocal;

public class EurocalSOAPSkeleton implements org.example.www.eurocal.Eurocal_PortType, org.apache.axis.wsdl.Skeleton {
    private org.example.www.eurocal.Eurocal_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "codi"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "moneda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("convertirAEuros", _params, new javax.xml.namespace.QName("", "out"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.example.org/eurocal/", "ConvertirAEuros"));
        _oper.setSoapAction("http://www.example.org/eurocal/ConvertirAEuros");
        _myOperationsList.add(_oper);
        if (_myOperations.get("convertirAEuros") == null) {
            _myOperations.put("convertirAEuros", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("convertirAEuros")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "codi"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "euros"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("convertirEuros", _params, new javax.xml.namespace.QName("", "out"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://www.example.org/eurocal/", "ConvertirEuros"));
        _oper.setSoapAction("http://www.example.org/eurocal/ConvertirEuros");
        _myOperationsList.add(_oper);
        if (_myOperations.get("convertirEuros") == null) {
            _myOperations.put("convertirEuros", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("convertirEuros")).add(_oper);
    }

    public EurocalSOAPSkeleton() {
        this.impl = new org.example.www.eurocal.EurocalSOAPImpl();
    }

    public EurocalSOAPSkeleton(org.example.www.eurocal.Eurocal_PortType impl) {
        this.impl = impl;
    }
    public double convertirAEuros(java.lang.String codi, double moneda) throws java.rmi.RemoteException
    {
        double ret = impl.convertirAEuros(codi, moneda);
        return ret;
    }

    public double convertirEuros(java.lang.String codi, double euros) throws java.rmi.RemoteException
    {
        double ret = impl.convertirEuros(codi, euros);
        return ret;
    }

}
