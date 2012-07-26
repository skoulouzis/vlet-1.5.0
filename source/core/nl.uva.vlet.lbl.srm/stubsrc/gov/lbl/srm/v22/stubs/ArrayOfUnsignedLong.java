/**
 * ArrayOfUnsignedLong.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class ArrayOfUnsignedLong implements java.io.Serializable
{
    private org.apache.axis.types.UnsignedLong[] unsignedLongArray;

    public ArrayOfUnsignedLong()
    {
    }

    public ArrayOfUnsignedLong(org.apache.axis.types.UnsignedLong[] unsignedLongArray)
    {
        this.unsignedLongArray = unsignedLongArray;
    }

    /**
     * Gets the unsignedLongArray value for this ArrayOfUnsignedLong.
     * 
     * @return unsignedLongArray
     */
    public org.apache.axis.types.UnsignedLong[] getUnsignedLongArray()
    {
        return unsignedLongArray;
    }

    /**
     * Sets the unsignedLongArray value for this ArrayOfUnsignedLong.
     * 
     * @param unsignedLongArray
     */
    public void setUnsignedLongArray(org.apache.axis.types.UnsignedLong[] unsignedLongArray)
    {
        this.unsignedLongArray = unsignedLongArray;
    }

    public org.apache.axis.types.UnsignedLong getUnsignedLongArray(int i)
    {
        return this.unsignedLongArray[i];
    }

    public void setUnsignedLongArray(int i, org.apache.axis.types.UnsignedLong _value)
    {
        this.unsignedLongArray[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof ArrayOfUnsignedLong))
            return false;
        ArrayOfUnsignedLong other = (ArrayOfUnsignedLong) obj;
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
        _equals = true && ((this.unsignedLongArray == null && other.getUnsignedLongArray() == null) || (this.unsignedLongArray != null && java.util.Arrays
                .equals(this.unsignedLongArray, other.getUnsignedLongArray())));
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
        if (getUnsignedLongArray() != null)
        {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getUnsignedLongArray()); i++)
            {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnsignedLongArray(), i);
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
            ArrayOfUnsignedLong.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfUnsignedLong"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsignedLongArray");
        elemField.setXmlName(new javax.xml.namespace.QName("", "unsignedLongArray"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedLong"));
        elemField.setNillable(true);
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
