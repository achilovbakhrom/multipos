package com.jim.multipos.Entity.Product;


public class ProductClass {
    private String name;
    private boolean isActive;
    private long sort;

    public ProductClass(String name, boolean isActive, long sort) {
        this.name = name;
        this.isActive = isActive;
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }
}
