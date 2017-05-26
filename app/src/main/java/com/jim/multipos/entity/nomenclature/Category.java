package com.jim.multipos.entity.nomenclature;


import android.content.Context;

import com.jim.multipos.entity.Entity;
import com.jim.multipos.managers.DatabaseManager;
import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Category implements Entity {

    private String id;
    private String parentId;
    private String name;
    public Category() { id = UUID.randomUUID().toString(); }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    @Override
    public void delete(Context context) {
        createTable(context);
        String sql = "DELETE FROM CATEGORY WHERE id = " + id + ";";
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE into CATEGORY (id, parentId, name) VALUES (" +
                id + ", " +
                parentId + ", " +
                name + ");";
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS CATEGORY (" +
                "id TEXT PRIMARY KEY NOT NULL," +
                "parentId TEXT," +
                "name TEXT," +
                "FOREIGN KEY (parentId) REFERENCES CATEGORY (id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    public static List<Category> getAll(Context context) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORY";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                Category category = new Category();
                category.setId(cursor.stringValue(0));
                category.setParentId(cursor.stringValue(1));
                category.setName(cursor.stringValue(2));
                categories.add(category);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static Category getCategoryById(Context context, String id) {
        Category category = null;
        String sql = "SELECT * FROM CATEGORY WHERE id = ?";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql, new Object[] {id});
        try {
            if (cursor.next()) {
                category = new Category();
                category.setId(cursor.stringValue(0));
                category.setParentId(cursor.stringValue(1));
                category.setName(cursor.stringValue(2));
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return category;
    }
}
