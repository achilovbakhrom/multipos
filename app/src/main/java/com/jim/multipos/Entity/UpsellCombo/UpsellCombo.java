package com.jim.multipos.Entity.UpsellCombo;


public class UpsellCombo {
    private String name;
    private double price;
    private String photoPath;

    public UpsellCombo(String name, double price, String photoPath) {
        this.name = name;
        this.price = price;
        this.photoPath = photoPath;
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

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
