package com.jim.multipos.Entity.Unit;


public class Unit {
    private String name;
    private String factorRoot;
    private boolean isActive;

    public Unit(String name, String factorRoot, boolean isActive) {
        this.name = name;
        this.factorRoot = factorRoot;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactorRoot() {
        return factorRoot;
    }

    public void setFactorRoot(String factorRoot) {
        this.factorRoot = factorRoot;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
