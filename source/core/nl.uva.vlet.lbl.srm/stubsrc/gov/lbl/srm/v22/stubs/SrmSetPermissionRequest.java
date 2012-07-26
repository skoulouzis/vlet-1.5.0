/**
 * SrmSetPermissionRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class SrmSetPermissionRequest implements java.io.Serializable
{
    private java.lang.String authorizationID;

    private org.apache.axis.types.URI SURL;

    private gov.lbl.srm.v22.stubs.TPermissionType permissionType;

    private gov.lbl.srm.v22.stubs.TPermissionMode ownerPermission;

    private gov.lbl.srm.v22.stubs.ArrayOfTUserPermission arrayOfUserPermissions;

    private gov.lbl.srm.v22.stubs.ArrayOfTGroupPermission arrayOfGroupPermissions;

    private gov.lbl.srm.v22.stubs.TPermissionMode otherPermission;

    private gov.lbl.srm.v22.stubs.ArrayOfTExtraInfo storageSystemInfo;

    public SrmSetPermissionRequest()
    {
    }

    public SrmSetPermissionRequest(java.lang.String authorizationID, org.apache.axis.types.URI SURL,
            gov.lbl.srm.v22.stubs.TPermissionType permissionType,
            gov.lbl.srm.v22.stubs.TPermissionMode ownerPermission,
            gov.lbl.srm.v22.stubs.ArrayOfTUserPermission arrayOfUserPermissions,
            gov.lbl.srm.v22.stubs.ArrayOfTGroupPermission arrayOfGroupPermissions,
            gov.lbl.srm.v22.stubs.TPermissionMode otherPermission,
            gov.lbl.srm.v22.stubs.ArrayOfTExtraInfo storageSystemInfo)
    {
        this.authorizationID = authorizationID;
        this.SURL = SURL;
        this.permissionType = permissionType;
        this.ownerPermission = ownerPermission;
        this.arrayOfUserPermissions = arrayOfUserPermissions;
        this.arrayOfGroupPermissions = arrayOfGroupPermissions;
        this.otherPermission = otherPermission;
        this.storageSystemInfo = storageSystemInfo;
    }

    /**
     * Gets the authorizationID value for this SrmSetPermissionRequest.
     * 
     * @return authorizationID
     */
    public java.lang.String getAuthorizationID()
    {
        return authorizationID;
    }

    /**
     * Sets the authorizationID value for this SrmSetPermissionRequest.
     * 
     * @param authorizationID
     */
    public void setAuthorizationID(java.lang.String authorizationID)
    {
        this.authorizationID = authorizationID;
    }

    /**
     * Gets the SURL value for this SrmSetPermissionRequest.
     * 
     * @return SURL
     */
    public org.apache.axis.types.URI getSURL()
    {
        return SURL;
    }

    /**
     * Sets the SURL value for this SrmSetPermissionRequest.
     * 
     * @param SURL
     */
    public void setSURL(org.apache.axis.types.URI SURL)
    {
        this.SURL = SURL;
    }

    /**
     * Gets the permissionType value for this SrmSetPermissionRequest.
     * 
     * @return permissionType
     */
    public gov.lbl.srm.v22.stubs.TPermissionType getPermissionType()
    {
        return permissionType;
    }

    /**
     * Sets the permissionType value for this SrmSetPermissionRequest.
     * 
     * @param permissionType
     */
    public void setPermissionType(gov.lbl.srm.v22.stubs.TPermissionType permissionType)
    {
        this.permissionType = permissionType;
    }

    /**
     * Gets the ownerPermission value for this SrmSetPermissionRequest.
     * 
     * @return ownerPermission
     */
    public gov.lbl.srm.v22.stubs.TPermissionMode getOwnerPermission()
    {
        return ownerPermission;
    }

    /**
     * Sets the ownerPermission value for this SrmSetPermissionRequest.
     * 
     * @param ownerPermission
     */
    public void setOwnerPermission(gov.lbl.srm.v22.stubs.TPermissionMode ownerPermission)
    {
        this.ownerPermission = ownerPermission;
    }

    /**
     * Gets the arrayOfUserPermissions value for this SrmSetPermissionRequest.
     * 
     * @return arrayOfUserPermissions
     */
    public gov.lbl.srm.v22.stubs.ArrayOfTUserPermission getArrayOfUserPermissions()
    {
        return arrayOfUserPermissions;
    }

    /**
     * Sets the arrayOfUserPermissions value for this SrmSetPermissionRequest.
     * 
     * @param arrayOfUserPermissions
     */
    public void setArrayOfUserPermissions(gov.lbl.srm.v22.stubs.ArrayOfTUserPermission arrayOfUserPermissions)
    {
        this.arrayOfUserPermissions = arrayOfUserPermissions;
    }

    /**
     * Gets the arrayOfGroupPermissions value for this SrmSetPermissionRequest.
     * 
     * @return arrayOfGroupPermissions
     */
    public gov.lbl.srm.v22.stubs.ArrayOfTGroupPermission getArrayOfGroupPermissions()
    {
        return arrayOfGroupPermissions;
    }

    /**
     * Sets the arrayOfGroupPermissions value for this SrmSetPermissionRequest.
     * 
     * @param arrayOfGroupPermissions
     */
    public void setArrayOfGroupPermissions(gov.lbl.srm.v22.stubs.ArrayOfTGroupPermission arrayOfGroupPermissions)
    {
        this.arrayOfGroupPermissions = arrayOfGroupPermissions;
    }

    /**
     * Gets the otherPermission value for this SrmSetPermissionRequest.
     * 
     * @return otherPermission
     */
    public gov.lbl.srm.v22.stubs.TPermissionMode getOtherPermission()
    {
        return otherPermission;
    }

    /**
     * Sets the otherPermission value for this SrmSetPermissionRequest.
     * 
     * @param otherPermission
     */
    public void setOtherPermission(gov.lbl.srm.v22.stubs.TPermissionMode otherPermission)
    {
        this.otherPermission = otherPermission;
    }

    /**
     * Gets the storageSystemInfo value for this SrmSetPermissionRequest.
     * 
     * @return storageSystemInfo
     */
    public gov.lbl.srm.v22.stubs.ArrayOfTExtraInfo getStorageSystemInfo()
    {
        return storageSystemInfo;
    }

    /**
     * Sets the storageSystemInfo value for this SrmSetPermissionRequest.
     * 
     * @param storageSystemInfo
     */
    public void setStorageSystemInfo(gov.lbl.srm.v22.stubs.ArrayOfTExtraInfo storageSystemInfo)
    {
        this.storageSystemInfo = storageSystemInfo;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof SrmSetPermissionRequest))
            return false;
        SrmSetPermissionRequest other = (SrmSetPermissionRequest) obj;
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
                && ((this.authorizationID == null && other.getAuthorizationID() == null) || (this.authorizationID != null && this.authorizationID
                        .equals(other.getAuthorizationID())))
                && ((this.SURL == null && other.getSURL() == null) || (this.SURL != null && this.SURL.equals(other
                        .getSURL())))
                && ((this.permissionType == null && other.getPermissionType() == null) || (this.permissionType != null && this.permissionType
                        .equals(other.getPermissionType())))
                && ((this.ownerPermission == null && other.getOwnerPermission() == null) || (this.ownerPermission != null && this.ownerPermission
                        .equals(other.getOwnerPermission())))
                && ((this.arrayOfUserPermissions == null && other.getArrayOfUserPermissions() == null) || (this.arrayOfUserPermissions != null && this.arrayOfUserPermissions
                        .equals(other.getArrayOfUserPermissions())))
                && ((this.arrayOfGroupPermissions == null && other.getArrayOfGroupPermissions() == null) || (this.arrayOfGroupPermissions != null && this.arrayOfGroupPermissions
                        .equals(other.getArrayOfGroupPermissions())))
                && ((this.otherPermission == null && other.getOtherPermission() == null) || (this.otherPermission != null && this.otherPermission
                        .equals(other.getOtherPermission())))
                && ((this.storageSystemInfo == null && other.getStorageSystemInfo() == null) || (this.storageSystemInfo != null && this.storageSystemInfo
                        .equals(other.getStorageSystemInfo())));
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
        if (getAuthorizationID() != null)
        {
            _hashCode += getAuthorizationID().hashCode();
        }
        if (getSURL() != null)
        {
            _hashCode += getSURL().hashCode();
        }
        if (getPermissionType() != null)
        {
            _hashCode += getPermissionType().hashCode();
        }
        if (getOwnerPermission() != null)
        {
            _hashCode += getOwnerPermission().hashCode();
        }
        if (getArrayOfUserPermissions() != null)
        {
            _hashCode += getArrayOfUserPermissions().hashCode();
        }
        if (getArrayOfGroupPermissions() != null)
        {
            _hashCode += getArrayOfGroupPermissions().hashCode();
        }
        if (getOtherPermission() != null)
        {
            _hashCode += getOtherPermission().hashCode();
        }
        if (getStorageSystemInfo() != null)
        {
            _hashCode += getStorageSystemInfo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            SrmSetPermissionRequest.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "srmSetPermissionRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorizationID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authorizationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("permissionType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "permissionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TPermissionType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownerPermission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ownerPermission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TPermissionMode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayOfUserPermissions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayOfUserPermissions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTUserPermission"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrayOfGroupPermissions");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrayOfGroupPermissions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTGroupPermission"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otherPermission");
        elemField.setXmlName(new javax.xml.namespace.QName("", "otherPermission"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TPermissionMode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storageSystemInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "storageSystemInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTExtraInfo"));
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
