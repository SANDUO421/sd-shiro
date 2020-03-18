/**
 * NotificationReq_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ategoryReqc;

public class NotificationReq_Type  implements java.io.Serializable {
    private java.lang.String COPID;

    private java.lang.String SOPID;

    private java.lang.String contentId;

    private java.lang.String message;

    private java.lang.Integer notification;

    private java.lang.Integer notificationType;

    public NotificationReq_Type() {
    }

    public NotificationReq_Type(
           java.lang.String COPID,
           java.lang.String SOPID,
           java.lang.String contentId,
           java.lang.String message,
           java.lang.Integer notification,
           java.lang.Integer notificationType) {
           this.COPID = COPID;
           this.SOPID = SOPID;
           this.contentId = contentId;
           this.message = message;
           this.notification = notification;
           this.notificationType = notificationType;
    }


    /**
     * Gets the COPID value for this NotificationReq_Type.
     * 
     * @return COPID
     */
    public java.lang.String getCOPID() {
        return COPID;
    }


    /**
     * Sets the COPID value for this NotificationReq_Type.
     * 
     * @param COPID
     */
    public void setCOPID(java.lang.String COPID) {
        this.COPID = COPID;
    }


    /**
     * Gets the SOPID value for this NotificationReq_Type.
     * 
     * @return SOPID
     */
    public java.lang.String getSOPID() {
        return SOPID;
    }


    /**
     * Sets the SOPID value for this NotificationReq_Type.
     * 
     * @param SOPID
     */
    public void setSOPID(java.lang.String SOPID) {
        this.SOPID = SOPID;
    }


    /**
     * Gets the contentId value for this NotificationReq_Type.
     * 
     * @return contentId
     */
    public java.lang.String getContentId() {
        return contentId;
    }


    /**
     * Sets the contentId value for this NotificationReq_Type.
     * 
     * @param contentId
     */
    public void setContentId(java.lang.String contentId) {
        this.contentId = contentId;
    }


    /**
     * Gets the message value for this NotificationReq_Type.
     * 
     * @return message
     */
    public java.lang.String getMessage() {
        return message;
    }


    /**
     * Sets the message value for this NotificationReq_Type.
     * 
     * @param message
     */
    public void setMessage(java.lang.String message) {
        this.message = message;
    }


    /**
     * Gets the notification value for this NotificationReq_Type.
     * 
     * @return notification
     */
    public java.lang.Integer getNotification() {
        return notification;
    }


    /**
     * Sets the notification value for this NotificationReq_Type.
     * 
     * @param notification
     */
    public void setNotification(java.lang.Integer notification) {
        this.notification = notification;
    }


    /**
     * Gets the notificationType value for this NotificationReq_Type.
     * 
     * @return notificationType
     */
    public java.lang.Integer getNotificationType() {
        return notificationType;
    }


    /**
     * Sets the notificationType value for this NotificationReq_Type.
     * 
     * @param notificationType
     */
    public void setNotificationType(java.lang.Integer notificationType) {
        this.notificationType = notificationType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof NotificationReq_Type)) return false;
        NotificationReq_Type other = (NotificationReq_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.COPID==null && other.getCOPID()==null) || 
             (this.COPID!=null &&
              this.COPID.equals(other.getCOPID()))) &&
            ((this.SOPID==null && other.getSOPID()==null) || 
             (this.SOPID!=null &&
              this.SOPID.equals(other.getSOPID()))) &&
            ((this.contentId==null && other.getContentId()==null) || 
             (this.contentId!=null &&
              this.contentId.equals(other.getContentId()))) &&
            ((this.message==null && other.getMessage()==null) || 
             (this.message!=null &&
              this.message.equals(other.getMessage()))) &&
            ((this.notification==null && other.getNotification()==null) || 
             (this.notification!=null &&
              this.notification.equals(other.getNotification()))) &&
            ((this.notificationType==null && other.getNotificationType()==null) || 
             (this.notificationType!=null &&
              this.notificationType.equals(other.getNotificationType())));
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
        if (getCOPID() != null) {
            _hashCode += getCOPID().hashCode();
        }
        if (getSOPID() != null) {
            _hashCode += getSOPID().hashCode();
        }
        if (getContentId() != null) {
            _hashCode += getContentId().hashCode();
        }
        if (getMessage() != null) {
            _hashCode += getMessage().hashCode();
        }
        if (getNotification() != null) {
            _hashCode += getNotification().hashCode();
        }
        if (getNotificationType() != null) {
            _hashCode += getNotificationType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(NotificationReq_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "NotificationReq"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("COPID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "COPID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SOPID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "SOPID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "contentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("message");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "message"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "notification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notificationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "notificationType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
