package com.jim.multipos.entity.nomenclature.combo;


import android.content.Context;

import com.google.gson.Gson;
import com.jim.multipos.entity.DbCache;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.managers.DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComboNomenclature implements Entity {

    private String id;
    private String name;
    private double price;
    private String photoPath;
    private List<Slot> components;

    public ComboNomenclature() {
        id = UUID.randomUUID().toString();
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
    public void addComponent(Slot slot) {
        if (components == null)
            components = new ArrayList<>();
        components.add(slot);
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO COMBO_NOMENCLATURE(id, name, price, photo_path) VALUES(" +
                id + ", " + name + ", " + price + ", " + photoPath + ");";
        for (Slot slot : components) {
            sql += "INSERT OR REPLACE INTO COMBO_NOMENCLATURE_AND_SLOT(combo_nomenclature_id, slot_id) VALUES (" +
                    id + ", " + slot.getId() + ");";
        }
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
    }

    @Override
    public void delete(Context context) {
        String sql = "DELETE FROM COMBO_NOMENCLATURE WHERE id = " + id;
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                    "COMBO_NOMENCLATURE(" +
                    "id TEXT PRIMARY KEY NOT NULL," +
                    "name TEXT NOT NULL," +
                    "price REAL," +
                    "photo_path TEXT" +
                ");";
        sql += "CREATE TABLE IF NOT EXISTS " +
                "COMBO_NOMENCLATURE_AND_SLOT(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "combo_nomenclature_id TEXT," +
                    "slot_id TEXT," +
                    "FOREIGN KEY(combo_nomenclature_id) REFERENCES COMBO_NOMENCLATURE(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY(slot_id) REFERENCES SLOT(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static ComboNomenclature createFromJson(String json) {
        return new Gson().fromJson(json, ComboNomenclature.class);
    }

    public static ComboNomenclature getComboNomenclatureById(Context context, String id) {
        return DbCache.getInstance(context).getComboNomenclatureById(id);
    }

    public static List<ComboNomenclature> getAll(Context context) {
        return DbCache.getInstance(context).getComboNomenclatures();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Slot> getComponents() {
        return components;
    }

    public void setComponents(List<Slot> components) {
        this.components = components;
    }
}
