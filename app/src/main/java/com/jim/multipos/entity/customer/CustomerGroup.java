package com.jim.multipos.entity.customer;


public class CustomerGroup {
    private String name;
    private long referenceID;
    private boolean isActive;
    private boolean isTaxFree;

    public CustomerGroup(String name, long referenceID, boolean isActive, boolean isTaxFree) {
        this.name = name;
        this.referenceID = referenceID;
        this.isActive = isActive;
        this.isTaxFree = isTaxFree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(long referenceID) {
        this.referenceID = referenceID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isTaxFree() {
        return isTaxFree;
    }

    public void setTaxFree(boolean taxFree) {
        isTaxFree = taxFree;
    }
}
