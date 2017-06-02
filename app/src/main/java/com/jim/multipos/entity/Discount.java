package com.jim.multipos.entity;


public class Discount {
    private String name;
    private String amountType;
    private double amount;
    private String applyType;
    private long barcode;
    private double maxAmount;
    private double minAmount;
    private boolean isTaxed;
    private boolean isActive;
    private boolean isWholesale;
    private boolean hasDiscountCode;
    private String startDate;
    private String endDate;
    private long requireNimber;
    private String qualificationType;

    public Discount(String name, String amountType, double amount, String applyType, long barcode, double maxAmount, double minAmount, boolean isTaxed, boolean isActive, boolean isWholesale,
                    boolean hasDiscountCode, String startDate, String endDate, long requireNimber, String qualificationType) {
        this.name = name;
        this.amountType = amountType;
        this.amount = amount;
        this.applyType = applyType;
        this.barcode = barcode;
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.isTaxed = isTaxed;
        this.isActive = isActive;
        this.isWholesale = isWholesale;
        this.hasDiscountCode = hasDiscountCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.requireNimber = requireNimber;
        this.qualificationType = qualificationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
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

    public boolean isWholesale() {
        return isWholesale;
    }

    public void setWholesale(boolean wholesale) {
        isWholesale = wholesale;
    }

    public boolean isHasDiscountCode() {
        return hasDiscountCode;
    }

    public void setHasDiscountCode(boolean hasDiscountCode) {
        this.hasDiscountCode = hasDiscountCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getRequireNimber() {
        return requireNimber;
    }

    public void setRequireNimber(long requireNimber) {
        this.requireNimber = requireNimber;
    }

    public String getQualificationType() {
        return qualificationType;
    }

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }
}
