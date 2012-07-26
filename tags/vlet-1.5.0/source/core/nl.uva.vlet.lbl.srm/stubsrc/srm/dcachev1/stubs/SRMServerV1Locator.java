/**
 * SRMServerV1Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package srm.dcachev1.stubs;

public class SRMServerV1Locator extends org.apache.axis.client.Service implements srm.dcachev1.stubs.SRMServerV1
{

    /**
     * diskCacheV111.srm.server.SRMServerV1 web service
     */

    public SRMServerV1Locator()
    {
    }

    public SRMServerV1Locator(org.apache.axis.EngineConfiguration config)
    {
        super(config);
    }

    public SRMServerV1Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName)
            throws javax.xml.rpc.ServiceException
    {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ISRM
    private java.lang.String ISRM_address = "http://131.225.13.36:24333/srm/managerv1";

    public java.lang.String getISRMAddress()
    {
        return ISRM_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ISRMWSDDServiceName = "ISRM";

    public java.lang.String getISRMWSDDServiceName()
    {
        return ISRMWSDDServiceName;
    }

    public void setISRMWSDDServiceName(java.lang.String name)
    {
        ISRMWSDDServiceName = name;
    }

    public srm.dcachev1.stubs.ISRM getISRM() throws javax.xml.rpc.ServiceException
    {
        java.net.URL endpoint;
        try
        {
            endpoint = new java.net.URL(ISRM_address);
        }
        catch (java.net.MalformedURLException e)
        {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getISRM(endpoint);
    }

    public srm.dcachev1.stubs.ISRM getISRM(java.net.URL portAddress) throws javax.xml.rpc.ServiceException
    {
        try
        {
            srm.dcachev1.stubs.ISRMStub _stub = new srm.dcachev1.stubs.ISRMStub(portAddress, this);
            _stub.setPortName(getISRMWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e)
        {
            return null;
        }
    }

    public void setISRMEndpointAddress(java.lang.String address)
    {
        ISRM_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has
     * no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException
    {
        try
        {
            if (srm.dcachev1.stubs.ISRM.class.isAssignableFrom(serviceEndpointInterface))
            {
                srm.dcachev1.stubs.ISRMStub _stub = new srm.dcachev1.stubs.ISRMStub(new java.net.URL(ISRM_address),
                        this);
                _stub.setPortName(getISRMWSDDServiceName());
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
        if ("ISRM".equals(inputPortName))
        {
            return getISRM();
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
        return new javax.xml.namespace.QName("http://srm.1.0.ns", "SRMServerV1");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts()
    {
        if (ports == null)
        {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://srm.1.0.ns", "ISRM"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address)
            throws javax.xml.rpc.ServiceException
    {

        if ("ISRM".equals(portName))
        {
            setISRMEndpointAddress(address);
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
