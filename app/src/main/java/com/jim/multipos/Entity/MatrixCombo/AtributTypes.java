package com.jim.multipos.Entity.MatrixCombo;


public class AtributTypes {
    private String name;
    private long sort;
    private boolean isActive;

    public AtributTypes(String name, long sort, boolean isActive) {
        this.name = name;
        this.sort = sort;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
