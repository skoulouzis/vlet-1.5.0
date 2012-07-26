/**
 * ArrayOfTSupportedTransferProtocol.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class ArrayOfTSupportedTransferProtocol implements java.io.Serializable
{
    private gov.lbl.srm.v22.stubs.TSupportedTransferProtocol[] protocolArray;

    public ArrayOfTSupportedTransferProtocol()
    {
    }

    public ArrayOfTSupportedTransferProtocol(gov.lbl.srm.v22.stubs.TSupportedTransferProtocol[] protocolArray)
    {
        this.protocolArray = protocolArray;
    }

    /**
     * Gets the protocolArray value for this ArrayOfTSupportedTransferProtocol.
     * 
     * @return protocolArray
     */
    public gov.lbl.srm.v22.stubs.TSupportedTransferProtocol[] getProtocolArray()
    {
        return protocolArray;
    }

    /**
     * Sets the protocolArray value for this ArrayOfTSupportedTransferProtocol.
     * 
     * @param protocolArray
     */
    public void setProtocolArray(gov.lbl.srm.v22.stubs.TSupportedTransferProtocol[] protocolArray)
    {
        this.protocolArray = protocolArray;
    }

    public gov.lbl.srm.v22.stubs.TSupportedTransferProtocol getProtocolArray(int i)
    {
        return this.protocolArray[i];
    }

    public void setProtocolArray(int i, gov.lbl.srm.v22.stubs.TSupportedTransferProtocol _value)
    {
        this.protocolArray[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj)
    {
        if (!(obj instanceof ArrayOfTSupportedTransferProtocol))
            return false;
        ArrayOfTSupportedTransferProtocol other = (ArrayOfTSupportedTransferProtocol) obj;
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
        _equals = true && ((this.protocolArray == null && other.getProtocolArray() == null) || (this.protocolArray != null && java.util.Arrays
                .equals(this.protocolArray, other.getProtocolArray())));
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
        if (getProtocolArray() != null)
        {
            for (int i = 0; i < java.lang.reflect.Array.getLength(getProtocolArray()); i++)
            {
                java.lang.Object obj = java.lang.reflect.Array.get(getProtocolArray(), i);
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
            ArrayOfTSupportedTransferProtocol.class, true);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "ArrayOfTSupportedTransferProtocol"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protocolArray");
        elemField.setXmlName(new javax.xml.namespace.QName("", "protocolArray"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TSupportedTransferProtocol"));
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
