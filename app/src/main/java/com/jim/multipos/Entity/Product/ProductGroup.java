package com.jim.multipos.Entity.Product;


public class ProductGroup {
    private boolean isActive;
    private String name;
    private boolean forDiscount;

    public ProductGroup(boolean isActive, String name, boolean forDiscuont) {
        this.isActive = isActive;
        this.name = name;
        this.forDiscount = forDiscuont;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isForDiscount() {
        return forDiscount;
    }

    public void setForDiscount(boolean forDiscount) {
        this.forDiscount = forDiscount;
    }
}
