package com.jim.multipos.entity;


import android.content.Context;

import java.util.List;
import java.util.UUID;

public class Vendor implements Entity{
    private String id;
    private String name;
    private String contactName;
    private String email;
    private String phoneNumber;
    private String address;
    private double taxRate;
    private boolean isActive;

    public Vendor() { id = UUID.randomUUID().toString(); }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getContactName() { return contactName; }
    public void setContactName(String contactName) { this.contactName = contactName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getTaxRate() { return taxRate; }
    public void setTaxRate(double taxRate) { this.taxRate = taxRate; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Override
    public void saveOrUpdate(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    @Override
    public void createTable(Context context) {

    }

    public static List<Vendor> getAll(Context context) {
        return null;
    }

    public static Vendor getVendorById(Context context, String id) {
        return null;
    }
}
