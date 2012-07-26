/**
 * SrmCopyResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class SrmCopyResponse implements java.io.Serializable
{
    private gov.lbl.srm.v22.stubs.TReturnStatus returnStatus;

    private java.lang.String requestToken;

    private gov.lbl.srm.v22.stubs.ArrayOfTCopyRequestFileStatus arrayOfFileStatuses;

    private java.lang.Integer remainingTotalRequestTime;

    public SrmCopyResponse()
    {
    }

    public SrmCopyResponse(gov.lbl.srm.v22.stubs.TReturnStatus returnStatus, java.lang.String requestToken,
            gov.lbl.srm.v22.stubs.ArrayOfTCopyRequestFileStatus arrayOfFileStatuses,
            java.lang.Integer remainingTotalRequestTime)
    {
        this.returnStatus = returnStatus;
        this.requestToken = requestToken;
        this.arrayOfFileStatuses = arrayOfFileStatuses;
        this.remainingTotalRequestTime = remainingTotalRequestTime;
    }

    /**
     * Gets the returnStatus value for this SrmCopyResponse.
     * 
     * @return returnStatus
     */
    public gov.lbl.srm.v22.stubs.TReturnStatus getReturnStatus()
    {
        return returnStatus;
    }

    /**
     * Sets the returnStatus value for this SrmCopyResponse.
     * 
     * @param returnStatus
     */
    public void setReturnStatus(gov.lbl.srm.v22.stubs.TReturnStatus returnStatus)
    {
        this.returnStatus = returnStatus;
    }

    /**
     * Gets the requestToken value for this SrmCopyResponse.
     * 
     * @return requestToken
     */
    public java.lang.String getRequestToken()
    {
        return requestToken;
    }

    /**
     * Sets the requestToken value for this SrmCopyResponse.
     * 
     * @param requestToken
     */
    public void setRequestToken(java.lang.String requestToken)
    {
        this.requestToken = requestToken;
    }

    /**
     * Gets the arrayOfFileStatuses value for this SrmCopyResponse.
     * 
     * @return arrayOfFileStatuses
     */
    public gov.lbl.srm.v22.stubs.ArrayOfTCopyRequestFileStatus getArrayOfFileStatuses()
    {
        return arrayOfFileStatuses;
    }

    /**
     * Sets the arrayOfFileStatuses value for this SrmCopyResponse.
     * 
     * @param arrayOfFileStatuses
     */
    public void setArrayOfFileStatuses(gov.lbl.srm.v22.stubs.ArrayOfTCopyRequestFileStatus arrayOfFileStatuses)
    {
        this.arrayOfFileStatuses = arrayOfFileStatuses;
    }

    /**
     * Gets the remainingTotalRequestTime value for this SrmCopyResponse.
     * 
     * @return remainingTotalRequestTime
     */
    public java.lang.Integer getRemainingTotalRequestTime()
    {
        return remainingTotalRequestTime;
    }

    /**
     * Sets the remainingTotalRequestTime value for this SrmCopyResponse.
     * 
     * @param remainingTotalRequestTime
     */
    public void setRemainingTotalRequestTime(java.lang.Integer remainingTotalRequestTime)
    {
        this.remainingTotalRequestTime = remainingTotalRequestTime;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof SrmCopyResponse))
            return false;
        SrmCopyResponse other = (SrmCopyResponse) obj;
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
                && ((this.arrayOfFileStatuses == null && other.getArrayOfFileStatuses() == null) || (this.arrayOfFileStatuses != null && this.arrayOfFileStatuses
                        .equals(other.getArrayOfFileStatuses())))
                && ((this.remainingTotalRequestTime == null && other.getRemainingTotalRequestTime() == null) || (this.remainingTotalRequestTime != null && this.remainingTotalRequestTime
                        .equals(other.getRemainingTotalRequestTime())));
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
        if (getArrayOfFileStatuses() != null)
        {
            _hashCode += getArrayOfFileStatuses().hashCode();
        }
        if (getRemainingTotalRequestTime() != null)
        {
            _hashCode += getRemainingTotalRequestTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            SrmCopyResponse.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "srmCopyResponse"));
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
        elemField.setFieldName("arrayOfFileStatuses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayOfFileStatuses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTCopyRequestFileStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remainingTotalRequestTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "remainingTotalRequestTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
