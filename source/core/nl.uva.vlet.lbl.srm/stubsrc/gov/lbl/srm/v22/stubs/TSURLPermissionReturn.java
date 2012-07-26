/**
 * TSURLPermissionReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class TSURLPermissionReturn implements java.io.Serializable
{
    private org.apache.axis.types.URI surl;

    private gov.lbl.srm.v22.stubs.TReturnStatus status;

    private gov.lbl.srm.v22.stubs.TPermissionMode permission;

    public TSURLPermissionReturn()
    {
    }

    public TSURLPermissionReturn(org.apache.axis.types.URI surl, gov.lbl.srm.v22.stubs.TReturnStatus status,
            gov.lbl.srm.v22.stubs.TPermissionMode permission)
    {
        this.surl = surl;
        this.status = status;
        this.permission = permission;
    }

    /**
     * Gets the surl value for this TSURLPermissionReturn.
     * 
     * @return surl
     */
    public org.apache.axis.types.URI getSurl()
    {
        return surl;
    }

    /**
     * Sets the surl value for this TSURLPermissionReturn.
     * 
     * @param surl
     */
    public void setSurl(org.apache.axis.types.URI surl)
    {
        this.surl = surl;
    }

    /**
     * Gets the status value for this TSURLPermissionReturn.
     * 
     * @return status
     */
    public gov.lbl.srm.v22.stubs.TReturnStatus getStatus()
    {
        return status;
    }

    /**
     * Sets the status value for this TSURLPermissionReturn.
     * 
     * @param status
     */
    public void setStatus(gov.lbl.srm.v22.stubs.TReturnStatus status)
    {
        this.status = status;
    }

    /**
     * Gets the permission value for this TSURLPermissionReturn.
     * 
     * @return permission
     */
    public gov.lbl.srm.v22.stubs.TPermissionMode getPermission()
    {
        return permission;
    }

    /**
     * Sets the permission value for this TSURLPermissionReturn.
     * 
     * @param permission
     */
    public void setPermission(gov.lbl.srm.v22.stubs.TPermissionMode permission)
    {
        this.permission = permission;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof TSURLPermissionReturn))
            return false;
        TSURLPermissionReturn other = (TSURLPermissionReturn) obj;
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
                && ((this.surl == null && other.getSurl() == null) || (this.surl != null && this.surl.equals(other
                        .getSurl())))
                && ((this.status == null && other.getStatus() == null) || (this.status != null && this.status
                        .equals(other.getStatus())))
                && ((this.permission == null && other.getPermission() == null) || (this.permission != null && this.permission
                        .equals(other.getPermission())));
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
        if (getSurl() != null)
        {
            _hashCode += getSurl().hashCode();
        }
        if (getStatus() != null)
        {
            _hashCode += getStatus().hashCode();
        }
        if (getPermission() != null)
        {
            _hashCode += getPermission().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            TSURLPermissionReturn.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TSURLPermissionReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surl");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("", "status"));
        elemField
                .setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager", "TReturnStatus"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TPermissionMode"));
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
