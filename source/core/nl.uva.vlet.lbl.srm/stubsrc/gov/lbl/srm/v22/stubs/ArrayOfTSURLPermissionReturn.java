/**
 * ArrayOfTSURLPermissionReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class ArrayOfTSURLPermissionReturn implements java.io.Serializable
{
    private gov.lbl.srm.v22.stubs.TSURLPermissionReturn[] surlPermissionArray;

    public ArrayOfTSURLPermissionReturn()
    {
    }

    public ArrayOfTSURLPermissionReturn(gov.lbl.srm.v22.stubs.TSURLPermissionReturn[] surlPermissionArray)
    {
        this.surlPermissionArray = surlPermissionArray;
    }

    /**
     * Gets the surlPermissionArray value for this ArrayOfTSURLPermissionReturn.
     * 
     * @return surlPermissionArray
     */
    public gov.lbl.srm.v22.stubs.TSURLPermissionReturn[] getSurlPermissionArray()
    {
        return surlPermissionArray;
    }

    /**
     * Sets the surlPermissionArray value for this ArrayOfTSURLPermissionReturn.
     * 
     * @param surlPermissionArray
     */
    public void setSurlPermissionArray(gov.lbl.srm.v22.stubs.TSURLPermissionReturn[] surlPermissionArray)
    {
        this.surlPermissionArray = surlPermissionArray;
    }

    public gov.lbl.srm.v22.stubs.TSURLPermissionReturn getSurlPermissionArray(int i)
    {
        return this.surlPermissionArray[i];
    }

    public void setSurlPermissionArray(int i, gov.lbl.srm.v22.stubs.TSURLPermissionReturn _value)
    {
        this.surlPermissionArray[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof ArrayOfTSURLPermissionReturn))
            return false;
        ArrayOfTSURLPermissionReturn other = (ArrayOfTSURLPermissionReturn) obj;
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
        _equals = true && ((this.surlPermissionArray == null && other.getSurlPermissionArray() == null) || (this.surlPermissionArray != null && java.util.Arrays
                .equals(this.surlPermissionArray, other.getSurlPermissionArray())));
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
        if (getSurlPermissionArray() != null)
        {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getSurlPermissionArray()); i++)
            {
                java.lang.Object obj = java.lang.reflect.Array.get(getSurlPermissionArray(), i);
                if (obj != null && !obj.getClass().isArray())
                {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            ArrayOfTSURLPermissionReturn.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTSURLPermissionReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("surlPermissionArray");
        elemField.setXmlName(new javax.xml.namespace.QName("", "surlPermissionArray"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TSURLPermissionReturn"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
