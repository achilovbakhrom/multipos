package com.jim.multipos.Entity.UpsellCombo;


public class UpsellProductList {
    private double plusAmount;

    public UpsellProductList(double plusAmount) {
        this.plusAmount = plusAmount;
    }

    public double getPlusAmount() {
        return plusAmount;
    }

    public void setPlusAmount(double plusAmount) {
        this.plusAmount = plusAmount;
    }
}
