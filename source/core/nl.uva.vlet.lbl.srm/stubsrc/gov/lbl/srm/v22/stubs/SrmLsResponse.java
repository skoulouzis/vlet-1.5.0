/**
 * SrmLsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class SrmLsResponse implements java.io.Serializable
{
    private gov.lbl.srm.v22.stubs.TReturnStatus returnStatus;

    private java.lang.String requestToken;

    private gov.lbl.srm.v22.stubs.ArrayOfTMetaDataPathDetail details;

    public SrmLsResponse()
    {
    }

    public SrmLsResponse(gov.lbl.srm.v22.stubs.TReturnStatus returnStatus, java.lang.String requestToken,
            gov.lbl.srm.v22.stubs.ArrayOfTMetaDataPathDetail details)
    {
        this.returnStatus = returnStatus;
        this.requestToken = requestToken;
        this.details = details;
    }

    /**
     * Gets the returnStatus value for this SrmLsResponse.
     * 
     * @return returnStatus
     */
    public gov.lbl.srm.v22.stubs.TReturnStatus getReturnStatus()
    {
        return returnStatus;
    }

    /**
     * Sets the returnStatus value for this SrmLsResponse.
     * 
     * @param returnStatus
     */
    public void setReturnStatus(gov.lbl.srm.v22.stubs.TReturnStatus returnStatus)
    {
        this.returnStatus = returnStatus;
    }

    /**
     * Gets the requestToken value for this SrmLsResponse.
     * 
     * @return requestToken
     */
    public java.lang.String getRequestToken()
    {
        return requestToken;
    }

    /**
     * Sets the requestToken value for this SrmLsResponse.
     * 
     * @param requestToken
     */
    public void setRequestToken(java.lang.String requestToken)
    {
        this.requestToken = requestToken;
    }

    /**
     * Gets the details value for this SrmLsResponse.
     * 
     * @return details
     */
    public gov.lbl.srm.v22.stubs.ArrayOfTMetaDataPathDetail getDetails()
    {
        return details;
    }

    /**
     * Sets the details value for this SrmLsResponse.
     * 
     * @param details
     */
    public void setDetails(gov.lbl.srm.v22.stubs.ArrayOfTMetaDataPathDetail details)
    {
        this.details = details;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof SrmLsResponse))
            return false;
        SrmLsResponse other = (SrmLsResponse) obj;
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
                && ((this.returnStatus == null && other.getReturnStatus() == null) || (this.returnStatus != null && this.returnStatus
                        .equals(other.getReturnStatus())))
                && ((this.requestToken == null && other.getRequestToken() == null) || (this.requestToken != null && this.requestToken
                        .equals(other.getRequestToken())))
                && ((this.details == null && other.getDetails() == null) || (this.details != null && this.details
                        .equals(other.getDetails())));
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
        if (getReturnStatus() != null)
        {
            _hashCode += getReturnStatus().hashCode();
        }
        if (getRequestToken() != null)
        {
            _hashCode += getRequestToken().hashCode();
        }
        if (getDetails() != null)
        {
            _hashCode += getDetails().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            SrmLsResponse.class, true);

    static
    {
        typeDesc
                .setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager", "srmLsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "returnStatus"));
        elemField
                .setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager", "TReturnStatus"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "requestToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTMetaDataPathDetail"));
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
