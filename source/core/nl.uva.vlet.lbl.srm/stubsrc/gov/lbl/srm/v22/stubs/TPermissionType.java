/**
 * TPermissionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 May 16, 2006 (03:42:07 EDT) WSDL2Java emitter.
 */

package gov.lbl.srm.v22.stubs;

public class TPermissionType implements java.io.Serializable
{
    private java.lang.String _value_;

    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected TPermissionType(java.lang.String value)
    {
        _value_ = value;
        _table_.put(_value_, this);
    }

    public static final java.lang.String _ADD = "ADD";

    public static final java.lang.String _REMOVE = "REMOVE";

    public static final java.lang.String _CHANGE = "CHANGE";

    public static final TPermissionType ADD = new TPermissionType(_ADD);

    public static final TPermissionType REMOVE = new TPermissionType(_REMOVE);

    public static final TPermissionType CHANGE = new TPermissionType(_CHANGE);

    public java.lang.String getValue()
    {
        return _value_;
    }

    public static TPermissionType fromValue(java.lang.String value) throws java.lang.IllegalArgumentException
    {
        TPermissionType enumeration = (TPermissionType) _table_.get(value);
        if (enumeration == null)
            throw new java.lang.IllegalArgumentException();
        return enumeration;
    }

    public static TPermissionType fromString(java.lang.String value) throws java.lang.IllegalArgumentException
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
            TPermissionType.class);

    static
    {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://srm.lbl.gov/StorageResourceManager",
                "TPermissionType"));
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc()
    {
        return typeDesc;
    }

}
