/**
 * SRMServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class SRMServiceLocator extends org.apache.axis.client.Service implements gov.lbl.srm.v22.stubs.SRMService
{

    public SRMServiceLocator()
    {
    }

    public SRMServiceLocator(org.apache.axis.EngineConfiguration config)
    {
        super(config);
    }

    public SRMServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
            throws javax.xml.rpc.ServiceException
    {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for srm

    /**
     * the following location of the service is specific to the particular
     * deployment and is not part of the specification
     */
    private java.lang.String srm_address = "https://localhost:8443/ogsa/services/srm";

    public java.lang.String getsrmAddress()
    {
        return srm_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String srmWSDDServiceName = "srm";

    public java.lang.String getsrmWSDDServiceName()
    {
        return srmWSDDServiceName;
    }

    public void setsrmWSDDServiceName(java.lang.String name)
    {
        srmWSDDServiceName = name;
    }

    public gov.lbl.srm.v22.stubs.ISRM getsrm() throws javax.xml.rpc.ServiceException
    {
        java.net.URL endpoint;
        try
        {
            endpoint = new java.net.URL(srm_address);
        }
        catch (java.net.MalformedURLException e)
        {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsrm(endpoint);
    }

    public gov.lbl.srm.v22.stubs.ISRM getsrm(java.net.URL portAddress) throws javax.xml.rpc.ServiceException
    {
        try
        {
            gov.lbl.srm.v22.stubs.SrmSoapBindingStub _stub = new gov.lbl.srm.v22.stubs.SrmSoapBindingStub(portAddress,
                    this);
            _stub.setPortName(getsrmWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e)
        {
            return null;
        }
    }

    public void setsrmEndpointAddress(java.lang.String address)
    {
        srm_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException
    {
        try
        {
            if (gov.lbl.srm.v22.stubs.ISRM.class.isAssignableFrom(serviceEndpointInterface))
            {
                gov.lbl.srm.v22.stubs.SrmSoapBindingStub _stub = new gov.lbl.srm.v22.stubs.SrmSoapBindingStub(
                        new java.net.URL(srm_address), this);
                _stub.setPortName(getsrmWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t)
        {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  "
                + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface)
            throws javax.xml.rpc.ServiceException
    {
        if (portName == null)
        {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("srm".equals(inputPortName))
        {
            return getsrm();
        }
        else
        {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName()
    {
        return new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager", "SRMService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts()
    {
        if (ports == null)
        {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager", "srm"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException
    {

        if ("srm".equals(portName))
        {
            setsrmEndpointAddress(address);
        }
        else
        { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException
    {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
