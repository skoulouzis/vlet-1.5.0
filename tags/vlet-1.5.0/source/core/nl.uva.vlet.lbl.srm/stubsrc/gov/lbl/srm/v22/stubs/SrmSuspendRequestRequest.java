/**
 * SrmSuspendRequestRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class SrmSuspendRequestRequest implements java.io.Serializable
{
    private java.lang.String requestToken;

    private java.lang.String authorizationID;

    public SrmSuspendRequestRequest()
    {
    }

    public SrmSuspendRequestRequest(java.lang.String requestToken, java.lang.String authorizationID)
    {
        this.requestToken = requestToken;
        this.authorizationID = authorizationID;
    }

    /**
     * Gets the requestToken value for this SrmSuspendRequestRequest.
     * 
     * @return requestToken
     */
    public java.lang.String getRequestToken()
    {
        return requestToken;
    }

    /**
     * Sets the requestToken value for this SrmSuspendRequestRequest.
     * 
     * @param requestToken
     */
    public void setRequestToken(java.lang.String requestToken)
    {
        this.requestToken = requestToken;
    }

    /**
     * Gets the authorizationID value for this SrmSuspendRequestRequest.
     * 
     * @return authorizationID
     */
    public java.lang.String getAuthorizationID()
    {
        return authorizationID;
    }

    /**
     * Sets the authorizationID value for this SrmSuspendRequestRequest.
     * 
     * @param authorizationID
     */
    public void setAuthorizationID(java.lang.String authorizationID)
    {
        this.authorizationID = authorizationID;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof SrmSuspendRequestRequest))
            return false;
        SrmSuspendRequestRequest other = (SrmSuspendRequestRequest) obj;
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (__equalsCalc != null)
        {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.requestToken == null && other.getRequestToken() == null) || (this.requestToken != null && this.requestToken
                        .equals(other.getRequestToken())))
                && ((this.authorizationID == null && other.getAuthorizationID() == null) || (this.authorizationID != null && this.authorizationID
                        .equals(other.getAuthorizationID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;

    public synchronized int hashCode()
    {
        if (__hashCodeCalc)
        {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getRequestToken() != null)
        {
            _hashCode += getRequestToken().hashCode();
        }
        if (getAuthorizationID() != null)
        {
            _hashCode += getAuthorizationID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            SrmSuspendRequestRequest.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "srmSuspendRequestRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authorizationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc()
    {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
            java.lang.Class _javaType, javax.xml.namespace.QName _xmlType)
    {
        return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
            java.lang.Class _javaType, javax.xml.namespace.QName _xmlType)
    {
        return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

}
