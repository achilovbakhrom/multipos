package com.jim.multipos.entity.nomenclature.matrix;


import android.content.Context;

import com.jim.multipos.entity.DbCache;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.utils.managers.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

public class Attribute implements Entity{

    private String id;
    private String name;
    private long sort;
    private boolean isActive;
    private List<String> values;

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
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public void addValue(String value) {
        if (values == null)
            values = new ArrayList<>();
        values.add(value);
    }
    public List<String> getValues() { return values; }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO ATTRIBUTE(id, name, sort, is_active) VALUES(" +
                id + ", " + name + ", " + sort + ", " + isActive + ");";
        DatabaseManager.getInstance(context).execSQL(sql);
        sql = "";
        for (String value : values) {
            sql += "INSERT OR REPLACE INTO ATTRIBUTE_AND_VALUE(attribute_id, value) VALUES(" +
                    id + ", " + value + ");";
        }
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
    }

    @Override
    public void delete(Context context) {
        String sql = "DELETE FROM ATTRIBUTE WHERE id = " + id;
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS ATTRIBUTE(" +
                "id TEXT PRIMARY KEY NOT NULL," +
                "name TEXT NOT NULL," +
                "sort INTEGER," +
                "is_active INTEGER);";
        sql += "CREATE TABLE IF NOT EXISTS ATTRIBUTE_AND_VALUE(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "attribute_id TEXT NOT NULL," +
                "value TEXT NOT NULL," +
                "FOREIGN KEY(attribute_id) REFERENCES ATTRIBUTE(id) ON DELETE CASCADE ON UPDATE CASCADE);";
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();

    }

    public static List<Attribute> getAll(Context context) {
        return DbCache.getInstance(context).getAttributes();
    }

    public static Attribute getAttributeById(Context context, String id) {
        return DbCache.getInstance(context).getAttributeById(id);
    }

}
