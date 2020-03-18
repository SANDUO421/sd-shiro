/**
 * ResultInfo1.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ategoryReqc;

public class ResultInfo1  implements java.io.Serializable {
    private java.lang.String correlateId;

    private java.lang.Integer resultCode;

    private java.lang.String resultDesc;

    public ResultInfo1() {
    }

    public ResultInfo1(
           java.lang.String correlateId,
           java.lang.Integer resultCode,
           java.lang.String resultDesc) {
           this.correlateId = correlateId;
           this.resultCode = resultCode;
           this.resultDesc = resultDesc;
    }


    /**
     * Gets the correlateId value for this ResultInfo1.
     * 
     * @return correlateId
     */
    public java.lang.String getCorrelateId() {
        return correlateId;
    }


    /**
     * Sets the correlateId value for this ResultInfo1.
     * 
     * @param correlateId
     */
    public void setCorrelateId(java.lang.String correlateId) {
        this.correlateId = correlateId;
    }


    /**
     * Gets the resultCode value for this ResultInfo1.
     * 
     * @return resultCode
     */
    public java.lang.Integer getResultCode() {
        return resultCode;
    }


    /**
     * Sets the resultCode value for this ResultInfo1.
     * 
     * @param resultCode
     */
    public void setResultCode(java.lang.Integer resultCode) {
        this.resultCode = resultCode;
    }


    /**
     * Gets the resultDesc value for this ResultInfo1.
     * 
     * @return resultDesc
     */
    public java.lang.String getResultDesc() {
        return resultDesc;
    }


    /**
     * Sets the resultDesc value for this ResultInfo1.
     * 
     * @param resultDesc
     */
    public void setResultDesc(java.lang.String resultDesc) {
        this.resultDesc = resultDesc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResultInfo1)) return false;
        ResultInfo1 other = (ResultInfo1) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.correlateId==null && other.getCorrelateId()==null) || 
             (this.correlateId!=null &&
              this.correlateId.equals(other.getCorrelateId()))) &&
            ((this.resultCode==null && other.getResultCode()==null) || 
             (this.resultCode!=null &&
              this.resultCode.equals(other.getResultCode()))) &&
            ((this.resultDesc==null && other.getResultDesc()==null) || 
             (this.resultDesc!=null &&
              this.resultDesc.equals(other.getResultDesc())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCorrelateId() != null) {
            _hashCode += getCorrelateId().hashCode();
        }
        if (getResultCode() != null) {
            _hashCode += getResultCode().hashCode();
        }
        if (getResultDesc() != null) {
            _hashCode += getResultDesc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResultInfo1.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "ResultInfo1"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlateId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "correlateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "resultCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resultDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "resultDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
