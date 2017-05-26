package com.jim.multipos.entity.nomenclature.ingridient;

import android.content.Context;

import com.google.gson.Gson;
import com.jim.multipos.entity.DbCache;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.entity.nomenclature.AbstractNomenclature;
import com.jim.multipos.managers.DatabaseManager;

import java.util.List;

/**
 * Created by Developer on 5/24/17.
 */

public class Ingridient extends AbstractNomenclature implements Entity {

    @Override
    public boolean isSellable() {
        return false;
    }

    @Override
    public String toJson() {
        return (new Gson()).toJson(this);
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO " +
                "INGRIDIENT(" +
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
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void delete(Context context) {
        String sql = "DELETE FROM INGRIDIENT " +
                "WHERE id = " + id;
        DatabaseManager.getInstance(context).execSQL(sql);
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                "INGRIDIENT (" +
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

    public static Ingridient getIngridientById(Context context, String id) {
        return DbCache.getInstance(context).getIngridientById(id);
    }

    public static List<Ingridient> getAll(Context context) {
        return DbCache.getInstance(context).getIngridients();
    }

    public static Ingridient createFromJson(String json) {
        return (new Gson()).fromJson(json, Ingridient.class);
    }
}
