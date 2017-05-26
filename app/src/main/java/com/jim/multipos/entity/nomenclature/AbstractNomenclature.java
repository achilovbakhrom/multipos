package com.jim.multipos.entity.nomenclature;

import java.util.UUID;

public abstract class AbstractNomenclature {

    protected String id = "",
                name = "",
                description = "",
                photoPath = "",
                groupId = "",
                classId = "",
                categoryId = "",
                vendorId = "";
    protected int minCount = 0;
    protected long sku = 0L,
                barCode = 0L,
                crossCode = 0L;
    protected double price = 0.0d,
                cost = 0.0d;
    protected boolean isActive = true;

    public AbstractNomenclature() {
        id = UUID.randomUUID().toString();
    }

    public abstract boolean isSellable();
    public abstract String toJson();
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }
    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }
    public String getClassId() { return classId; }
    public void setClassId(String classId) { this.classId = classId;}
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
    public String getVendorId() { return vendorId; }
    public void setVendorId(String vendorId) { this.vendorId = vendorId; }
    public int getMinCount() { return minCount; }
    public void setMinCount(int minCount) { this.minCount = minCount; }
    public long getSku() { return sku; }
    public void setSku(long sku) { this.sku = sku; }
    public long getBarCode() { return barCode; }
    public void setBarCode(long barCode) { this.barCode = barCode; }
    public long getCrossCode() { return crossCode; }
    public void setCrossCode(long crossCode) { this.crossCode = crossCode; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}
