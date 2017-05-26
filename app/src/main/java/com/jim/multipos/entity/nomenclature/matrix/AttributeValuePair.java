package com.jim.multipos.entity.nomenclature.matrix;

/**
 * Created by Developer on 5/23/17.
 */

public class AttributeValuePair {
    private Attribute attribute;
    private String value;
    public Attribute getAttribute() { return attribute; }
    public void setAttribute(Attribute attribute) { this.attribute = attribute; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
