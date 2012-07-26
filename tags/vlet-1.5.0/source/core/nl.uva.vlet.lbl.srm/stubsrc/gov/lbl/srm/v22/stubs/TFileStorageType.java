/**
 * TFileStorageType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class TFileStorageType implements java.io.Serializable
{
    private java.lang.String _value_;

    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected TFileStorageType(java.lang.String value)
    {
        _value_ = value;
        _table_.put(_value_, this);
    }

    public static final java.lang.String _VOLATILE = "VOLATILE";

    public static final java.lang.String _DURABLE = "DURABLE";

    public static final java.lang.String _PERMANENT = "PERMANENT";

    public static final TFileStorageType VOLATILE = new TFileStorageType(_VOLATILE);

    public static final TFileStorageType DURABLE = new TFileStorageType(_DURABLE);

    public static final TFileStorageType PERMANENT = new TFileStorageType(_PERMANENT);

    public java.lang.String getValue()
    {
        return _value_;
    }

    public static TFileStorageType fromValue(java.lang.String value) throws java.lang.IllegalArgumentException
    {
        TFileStorageType enumeration = (TFileStorageType) _table_.get(value);
        if (enumeration == null)
            throw new java.lang.IllegalArgumentException();
        return enumeration;
    }

    public static TFileStorageType fromString(java.lang.String value) throws java.lang.IllegalArgumentException
    {
        return fromValue(value);
    }

    public boolean equals(java.lang.Object obj)
    {
        return (obj == this);
    }

    public int hashCode()
    {
        return toString().hashCode();
    }

    public java.lang.String toString()
    {
        return _value_;
    }

    public java.lang.Object readResolve() throws java.io.ObjectStreamException
    {
        return fromValue(_value_);
    }

    public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType,
            java.lang.Class _javaType, javax.xml.namespace.QName _xmlType)
    {
        return new org.apache.axis.encoding.ser.EnumSerializer(_javaType, _xmlType);
    }

    public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType,
            java.lang.Class _javaType, javax.xml.namespace.QName _xmlType)
    {
        return new org.apache.axis.encoding.ser.EnumDeserializer(_javaType, _xmlType);
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(
            TFileStorageType.class);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TFileStorageType"));
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc()
    {
        return typeDesc;
    }

}
