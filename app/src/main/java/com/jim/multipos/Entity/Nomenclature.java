package com.jim.multipos.Entity;


public class Nomenclature {
    private String name;
    private double price;
    private double cost;
    private long barcode;
    private long sku;
    private String photoPath;
    private boolean isActive;
    private boolean hasRecipe;
    private boolean isIngredient;

    public Nomenclature(String name, double price, double cost, long barcode, long sku,
                        String photoPath, boolean isActive, boolean hasRecipe, boolean isIngredient) {
        this.name = name;
        this.price = price;
        this.cost = cost;
        this.barcode = barcode;
        this.sku = sku;
        this.photoPath = photoPath;
        this.isActive = isActive;
        this.hasRecipe = hasRecipe;
        this.isIngredient = isIngredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getBarcode() {
        return barcode;
    }

    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    public long getSku() {
        return sku;
    }

    public void setSku(long sku) {
        this.sku = sku;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isHasRecipe() {
        return hasRecipe;
    }

    public void setHasRecipe(boolean hasRecipe) {
        this.hasRecipe = hasRecipe;
    }

    public boolean isIngredient() {
        return isIngredient;
    }

    public void setIngredient(boolean ingredient) {
        isIngredient = ingredient;
    }
}
