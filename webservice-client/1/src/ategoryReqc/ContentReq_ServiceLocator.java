/**
 * ContentReq_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ategoryReqc;

public class ContentReq_ServiceLocator extends org.apache.axis.client.Service implements ategoryReqc.ContentReq_Service {

    public ContentReq_ServiceLocator() {
    }


    public ContentReq_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ContentReq_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ContentReqHttpSoap11Endpoint
    private java.lang.String ContentReqHttpSoap11Endpoint_address = "http://124.116.129.62:8093/SxIptvSOAP/services/ContentReq.ContentReqHttpSoap11Endpoint/";

    public java.lang.String getContentReqHttpSoap11EndpointAddress() {
        return ContentReqHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ContentReqHttpSoap11EndpointWSDDServiceName = "ContentReqHttpSoap11Endpoint";

    public java.lang.String getContentReqHttpSoap11EndpointWSDDServiceName() {
        return ContentReqHttpSoap11EndpointWSDDServiceName;
    }

    public void setContentReqHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        ContentReqHttpSoap11EndpointWSDDServiceName = name;
    }

    public ategoryReqc.ContentReqPortType getContentReqHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ContentReqHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getContentReqHttpSoap11Endpoint(endpoint);
    }

    public ategoryReqc.ContentReqPortType getContentReqHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ategoryReqc.ContentReqSoap11BindingStub _stub = new ategoryReqc.ContentReqSoap11BindingStub(portAddress, this);
            _stub.setPortName(getContentReqHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setContentReqHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        ContentReqHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ategoryReqc.ContentReqPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                ategoryReqc.ContentReqSoap11BindingStub _stub = new ategoryReqc.ContentReqSoap11BindingStub(new java.net.URL(ContentReqHttpSoap11Endpoint_address), this);
                _stub.setPortName(getContentReqHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ContentReqHttpSoap11Endpoint".equals(inputPortName)) {
            return getContentReqHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.idp.com", "ContentReq");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.idp.com", "ContentReqHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ContentReqHttpSoap11Endpoint".equals(portName)) {
            setContentReqHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
