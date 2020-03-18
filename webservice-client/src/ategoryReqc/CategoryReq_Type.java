/**
 * CategoryReq_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ategoryReqc;

public class CategoryReq_Type  implements java.io.Serializable {
    private java.lang.String COPID;

    private java.lang.String SOPID;

    private java.lang.String action;

    private java.lang.String categoryId;

    private java.lang.String categoryName;

    private java.lang.Integer categoryType;

    private java.lang.String correlateId;

    private java.lang.String customerId;

    private java.lang.String description;

    private java.lang.String fatherId;

    private java.lang.String fatherIds;

    private java.lang.Integer hidden;

    private java.lang.String introduce;

    private ategoryReqc.Poster[] posters;

    private java.lang.Integer sortIndex;

    public CategoryReq_Type() {
    }

    public CategoryReq_Type(
           java.lang.String COPID,
           java.lang.String SOPID,
           java.lang.String action,
           java.lang.String categoryId,
           java.lang.String categoryName,
           java.lang.Integer categoryType,
           java.lang.String correlateId,
           java.lang.String customerId,
           java.lang.String description,
           java.lang.String fatherId,
           java.lang.String fatherIds,
           java.lang.Integer hidden,
           java.lang.String introduce,
           ategoryReqc.Poster[] posters,
           java.lang.Integer sortIndex) {
           this.COPID = COPID;
           this.SOPID = SOPID;
           this.action = action;
           this.categoryId = categoryId;
           this.categoryName = categoryName;
           this.categoryType = categoryType;
           this.correlateId = correlateId;
           this.customerId = customerId;
           this.description = description;
           this.fatherId = fatherId;
           this.fatherIds = fatherIds;
           this.hidden = hidden;
           this.introduce = introduce;
           this.posters = posters;
           this.sortIndex = sortIndex;
    }


    /**
     * Gets the COPID value for this CategoryReq_Type.
     * 
     * @return COPID
     */
    public java.lang.String getCOPID() {
        return COPID;
    }


    /**
     * Sets the COPID value for this CategoryReq_Type.
     * 
     * @param COPID
     */
    public void setCOPID(java.lang.String COPID) {
        this.COPID = COPID;
    }


    /**
     * Gets the SOPID value for this CategoryReq_Type.
     * 
     * @return SOPID
     */
    public java.lang.String getSOPID() {
        return SOPID;
    }


    /**
     * Sets the SOPID value for this CategoryReq_Type.
     * 
     * @param SOPID
     */
    public void setSOPID(java.lang.String SOPID) {
        this.SOPID = SOPID;
    }


    /**
     * Gets the action value for this CategoryReq_Type.
     * 
     * @return action
     */
    public java.lang.String getAction() {
        return action;
    }


    /**
     * Sets the action value for this CategoryReq_Type.
     * 
     * @param action
     */
    public void setAction(java.lang.String action) {
        this.action = action;
    }


    /**
     * Gets the categoryId value for this CategoryReq_Type.
     * 
     * @return categoryId
     */
    public java.lang.String getCategoryId() {
        return categoryId;
    }


    /**
     * Sets the categoryId value for this CategoryReq_Type.
     * 
     * @param categoryId
     */
    public void setCategoryId(java.lang.String categoryId) {
        this.categoryId = categoryId;
    }


    /**
     * Gets the categoryName value for this CategoryReq_Type.
     * 
     * @return categoryName
     */
    public java.lang.String getCategoryName() {
        return categoryName;
    }


    /**
     * Sets the categoryName value for this CategoryReq_Type.
     * 
     * @param categoryName
     */
    public void setCategoryName(java.lang.String categoryName) {
        this.categoryName = categoryName;
    }


    /**
     * Gets the categoryType value for this CategoryReq_Type.
     * 
     * @return categoryType
     */
    public java.lang.Integer getCategoryType() {
        return categoryType;
    }


    /**
     * Sets the categoryType value for this CategoryReq_Type.
     * 
     * @param categoryType
     */
    public void setCategoryType(java.lang.Integer categoryType) {
        this.categoryType = categoryType;
    }


    /**
     * Gets the correlateId value for this CategoryReq_Type.
     * 
     * @return correlateId
     */
    public java.lang.String getCorrelateId() {
        return correlateId;
    }


    /**
     * Sets the correlateId value for this CategoryReq_Type.
     * 
     * @param correlateId
     */
    public void setCorrelateId(java.lang.String correlateId) {
        this.correlateId = correlateId;
    }


    /**
     * Gets the customerId value for this CategoryReq_Type.
     * 
     * @return customerId
     */
    public java.lang.String getCustomerId() {
        return customerId;
    }


    /**
     * Sets the customerId value for this CategoryReq_Type.
     * 
     * @param customerId
     */
    public void setCustomerId(java.lang.String customerId) {
        this.customerId = customerId;
    }


    /**
     * Gets the description value for this CategoryReq_Type.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this CategoryReq_Type.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the fatherId value for this CategoryReq_Type.
     * 
     * @return fatherId
     */
    public java.lang.String getFatherId() {
        return fatherId;
    }


    /**
     * Sets the fatherId value for this CategoryReq_Type.
     * 
     * @param fatherId
     */
    public void setFatherId(java.lang.String fatherId) {
        this.fatherId = fatherId;
    }


    /**
     * Gets the fatherIds value for this CategoryReq_Type.
     * 
     * @return fatherIds
     */
    public java.lang.String getFatherIds() {
        return fatherIds;
    }


    /**
     * Sets the fatherIds value for this CategoryReq_Type.
     * 
     * @param fatherIds
     */
    public void setFatherIds(java.lang.String fatherIds) {
        this.fatherIds = fatherIds;
    }


    /**
     * Gets the hidden value for this CategoryReq_Type.
     * 
     * @return hidden
     */
    public java.lang.Integer getHidden() {
        return hidden;
    }


    /**
     * Sets the hidden value for this CategoryReq_Type.
     * 
     * @param hidden
     */
    public void setHidden(java.lang.Integer hidden) {
        this.hidden = hidden;
    }


    /**
     * Gets the introduce value for this CategoryReq_Type.
     * 
     * @return introduce
     */
    public java.lang.String getIntroduce() {
        return introduce;
    }


    /**
     * Sets the introduce value for this CategoryReq_Type.
     * 
     * @param introduce
     */
    public void setIntroduce(java.lang.String introduce) {
        this.introduce = introduce;
    }


    /**
     * Gets the posters value for this CategoryReq_Type.
     * 
     * @return posters
     */
    public ategoryReqc.Poster[] getPosters() {
        return posters;
    }


    /**
     * Sets the posters value for this CategoryReq_Type.
     * 
     * @param posters
     */
    public void setPosters(ategoryReqc.Poster[] posters) {
        this.posters = posters;
    }

    public ategoryReqc.Poster getPosters(int i) {
        return this.posters[i];
    }

    public void setPosters(int i, ategoryReqc.Poster _value) {
        this.posters[i] = _value;
    }


    /**
     * Gets the sortIndex value for this CategoryReq_Type.
     * 
     * @return sortIndex
     */
    public java.lang.Integer getSortIndex() {
        return sortIndex;
    }


    /**
     * Sets the sortIndex value for this CategoryReq_Type.
     * 
     * @param sortIndex
     */
    public void setSortIndex(java.lang.Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CategoryReq_Type)) return false;
        CategoryReq_Type other = (CategoryReq_Type) obj;
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
            ((this.action==null && other.getAction()==null) || 
             (this.action!=null &&
              this.action.equals(other.getAction()))) &&
            ((this.categoryId==null && other.getCategoryId()==null) || 
             (this.categoryId!=null &&
              this.categoryId.equals(other.getCategoryId()))) &&
            ((this.categoryName==null && other.getCategoryName()==null) || 
             (this.categoryName!=null &&
              this.categoryName.equals(other.getCategoryName()))) &&
            ((this.categoryType==null && other.getCategoryType()==null) || 
             (this.categoryType!=null &&
              this.categoryType.equals(other.getCategoryType()))) &&
            ((this.correlateId==null && other.getCorrelateId()==null) || 
             (this.correlateId!=null &&
              this.correlateId.equals(other.getCorrelateId()))) &&
            ((this.customerId==null && other.getCustomerId()==null) || 
             (this.customerId!=null &&
              this.customerId.equals(other.getCustomerId()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.fatherId==null && other.getFatherId()==null) || 
             (this.fatherId!=null &&
              this.fatherId.equals(other.getFatherId()))) &&
            ((this.fatherIds==null && other.getFatherIds()==null) || 
             (this.fatherIds!=null &&
              this.fatherIds.equals(other.getFatherIds()))) &&
            ((this.hidden==null && other.getHidden()==null) || 
             (this.hidden!=null &&
              this.hidden.equals(other.getHidden()))) &&
            ((this.introduce==null && other.getIntroduce()==null) || 
             (this.introduce!=null &&
              this.introduce.equals(other.getIntroduce()))) &&
            ((this.posters==null && other.getPosters()==null) || 
             (this.posters!=null &&
              java.util.Arrays.equals(this.posters, other.getPosters()))) &&
            ((this.sortIndex==null && other.getSortIndex()==null) || 
             (this.sortIndex!=null &&
              this.sortIndex.equals(other.getSortIndex())));
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
        if (getAction() != null) {
            _hashCode += getAction().hashCode();
        }
        if (getCategoryId() != null) {
            _hashCode += getCategoryId().hashCode();
        }
        if (getCategoryName() != null) {
            _hashCode += getCategoryName().hashCode();
        }
        if (getCategoryType() != null) {
            _hashCode += getCategoryType().hashCode();
        }
        if (getCorrelateId() != null) {
            _hashCode += getCorrelateId().hashCode();
        }
        if (getCustomerId() != null) {
            _hashCode += getCustomerId().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getFatherId() != null) {
            _hashCode += getFatherId().hashCode();
        }
        if (getFatherIds() != null) {
            _hashCode += getFatherIds().hashCode();
        }
        if (getHidden() != null) {
            _hashCode += getHidden().hashCode();
        }
        if (getIntroduce() != null) {
            _hashCode += getIntroduce().hashCode();
        }
        if (getPosters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPosters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPosters(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getSortIndex() != null) {
            _hashCode += getSortIndex().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CategoryReq_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "CategoryReq"));
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
        elemField.setFieldName("action");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "action"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "categoryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "categoryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "categoryType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correlateId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "correlateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "customerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatherId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "fatherId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fatherIds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "fatherIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hidden");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "hidden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("introduce");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "introduce"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "posters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "Poster"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sortIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entry.idp.com/xsd", "sortIndex"));
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
