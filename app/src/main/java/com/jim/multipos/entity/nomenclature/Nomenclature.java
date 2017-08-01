package com.jim.multipos.entity.nomenclature;

import android.content.Context;

import com.google.gson.Gson;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.utils.managers.DatabaseManager;

import java.util.List;

/**
 * Created by Developer on 5/24/17.
 */

public class Nomenclature extends AbstractNomenclature implements Entity {

    @Override
    public boolean isSellable() {
        return true;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Nomenclature createFromJson(String json) {
        return new Gson().fromJson(json, Nomenclature.class);
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO " +
                "NOMENCLATURE(" +
                    "id, " +
                    "name, " +
                    "description, " +
                    "photo_path, " +
                    "group_id, " +
                    "class_id, " +
                    "category_id, " +
                    "vendor_id, " +
                    "min_count, " +
                    "sku, " +
                    "bar_code, " +
                    "cross_code, " +
                    "cost, " +
                    "price, " +
                    "is_active) " +
                "VALUES (" +
                    id + ", " +
                    name + ", " +
                    photoPath + ", " +
                    groupId + ", " +
                    classId + ", " +
                    categoryId + ", " +
                    vendorId + ", " +
                    minCount + ", " +
                    sku + ", " +
                    barCode + ", " +
                    crossCode + ", " +
                    price + ", " +
                    cost + ", " +
                    isActive + ");";
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    @Override
    public void delete(Context context) {
        String sql = "DELETE FROM NOMENCLATURE WHERE id = " + id;
        DatabaseManager.getInstance(context).execSQL(sql);
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                "NOMENCLATURE (" +
                    "id TEXT PRIMARY KEY NOT NULL," +
                    "name TEXT NOT NULL," +
                    "description TEXT," +
                    "photo_path TEXT," +
                    "group_id TEXT," +
                    "class_id TEXT," +
                    "category_id TEXT," +
                    "vendor_id TEXT ON UPDATE CASCADE," +
                    "min_count INTEGER," +
                    "sku INTEGER," +
                    "bar_code INTEGER," +
                    "cross_code INTEGER," +
                    "cost REAL," +
                    "price REAL," +
                    "is_active INTEGER," +
                    "FOREIGN KEY(group_id) REFERENCES GROUP(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY(class_id) REFERENCES CLASS(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY(category_id) REFERENCES CATEGORY(id) ON UPDATE CASCADE," +
                    "FOREIGN KEY(vendor_id) REFERENCES VENDOR(id) ON UPDATE CASCADE" +
                ");";
        DatabaseManager.getInstance(context).execSQL(sql);
    }
    public static Nomenclature getNomenclatureById(Context context, String id) {
        return null;
    }

    public static List<Nomenclature> getAll(Context context) {
        return null;
    }
}
