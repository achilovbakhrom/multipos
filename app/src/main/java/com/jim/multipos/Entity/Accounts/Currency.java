package com.jim.multipos.Entity.Accounts;


public class Currency {
    private String name;
    private String code;
    private boolean isMain;
    private double exchangeRate;

    public Currency(String name, String code, boolean isMain, double exchangeRate) {
        this.name = name;
        this.code = code;
        this.isMain = isMain;
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
