package com.jim.multipos.entity;


public class ServiceFee {
    private String name;
    private double amount;
    private String type;
    private String usedFor;
    private String applyingType;
    private boolean isTaxed;
    private boolean isActive;

    public ServiceFee(String name, double amount, String type,
                      String usedFor, String applyingType, boolean isTaxed, boolean isActive) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.usedFor = usedFor;
        this.applyingType = applyingType;
        this.isTaxed = isTaxed;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsedFor() {
        return usedFor;
    }

    public void setUsedFor(String usedFor) {
        this.usedFor = usedFor;
    }

    public String getApplyingType() {
        return applyingType;
    }

    public void setApplyingType(String applyingType) {
        this.applyingType = applyingType;
    }

    public boolean isTaxed() {
        return isTaxed;
    }

    public void setTaxed(boolean taxed) {
        isTaxed = taxed;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
