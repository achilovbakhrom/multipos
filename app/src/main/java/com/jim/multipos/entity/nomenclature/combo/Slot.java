package com.jim.multipos.entity.nomenclature.combo;

import android.content.Context;

import com.google.gson.Gson;
import com.jim.multipos.entity.DbCache;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.managers.DatabaseManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Slot implements Entity{

    private String id, name, photoPath;
    private Map<String, Double> items;
    public Slot() {
        id = UUID.randomUUID().toString();
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    public void addItem(String nomenclatureId, double margin) {
        if (items == null)
            items = new HashMap<>();
        items.put(nomenclatureId, margin);
    }

    public Map<String, Double> getItems() {
        return items;
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO SLOT (id, name, photo_path) VALUES (" +
                id +", " + name + ", " + photoPath + ");";
        for (String nomenclatureId : items.keySet()) {
            sql += "INSERT OR REPLACE INTO SLOT_AND_NOMENCLATURE(slot_id, nomenclature_id, margin) VALUES(" +
                    id + ", " + nomenclatureId + ", " + items.get(nomenclatureId);
        }
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void delete(Context context) {
        String sql = "DELTE FROM SLOT WHERE id = " + id;
        DatabaseManager.getInstance(context).execSQL(sql);
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS" +
                "SLOT(" +
                    "id TEXT PRIMARY KEY NOT NULL, " +
                    "name TEXT," +
                    "photo_path TEXT);";
        sql += "CREATE TABLE IF NOT EXISTS" +
                    "SLOT_AND_NOMENCLATURE(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "slot_id TEXT NOT NULL, " +
                        "nomenclature_id TEXT NOT NULL," +
                        "margin REAL" +
                        "FOREIGN KEY(slot_id) REFERENCES SLOT(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                        "FOREIGN KEY(nomenclature_id) REFERENCES NOMENCLATURE(id) ON DELETE CASCADE ON UPDATE CASCADE);";
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
    }

    public void setItems(Map<String, Double> items) {
        this.items = items;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Slot createFromJson(String json) {
        return new Gson().fromJson(json, Slot.class);
    }

    public static Slot getSlotById(Context context, String id) {
        return DbCache.getInstance(context).getSlotById(id);
    }

    public static List<Slot> getAll(Context context) {
        return DbCache.getInstance(context).getSlots();
    }
}
