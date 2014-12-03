/**
 * EurocalSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.eurocal;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import NET.webserviceX.www.Currency;
import NET.webserviceX.www.CurrencyConvertorLocator;
import NET.webserviceX.www.CurrencyConvertorSoap;

public class EurocalSOAPImpl implements org.example.www.eurocal.Eurocal_PortType{
	public double convertirAEuros(java.lang.String codi, double moneda) throws java.rmi.RemoteException {
    	CurrencyConvertorLocator service;
		CurrencyConvertorSoap stub;
		double rate = 0;
		service = new CurrencyConvertorLocator();
		try {
			stub = service.getCurrencyConvertorSoap();
		} catch (ServiceException e) {
			e.printStackTrace();
			return -1.0;
		}
		try {
			rate = stub.conversionRate(Currency.fromString(codi), Currency.EUR);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return moneda * rate;
    }

    public double convertirEuros(java.lang.String codi, double euros) throws java.rmi.RemoteException {
    	CurrencyConvertorLocator service;
		CurrencyConvertorSoap stub;
		double rate = 0;
		service = new CurrencyConvertorLocator();
		try {
			stub = service.getCurrencyConvertorSoap();
		} catch (ServiceException e) {
			e.printStackTrace();
			return -1.0;
		}
		try {
			rate = stub.conversionRate(Currency.EUR, Currency.fromString(codi));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return euros * rate;
    }

}
