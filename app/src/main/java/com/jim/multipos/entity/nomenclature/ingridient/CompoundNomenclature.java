package com.jim.multipos.entity.nomenclature.ingridient;

import android.content.Context;

import com.google.gson.Gson;
import com.jim.multipos.entity.DbCache;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.entity.nomenclature.AbstractNomenclature;
import com.jim.multipos.utils.managers.DatabaseManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Developer on 5/24/17.
 */

public class CompoundNomenclature extends AbstractNomenclature implements Entity{

    private List<String> ingridients;

    public static CompoundNomenclature createFromJson(String json) {
        return (new Gson()).fromJson(json, CompoundNomenclature.class);
    }

    @Override
    public boolean isSellable() {
        return true;
    }

    @Override
    public String toJson() {
        return (new Gson()).toJson(this);
    }

    public void addIngridient(Ingridient ingridient) {
        if (ingridients == null)
            ingridients = new ArrayList<>();
        ingridients.add(ingridient.getId());
    }

    public void addIngridient(String ingridientId) {
        if (ingridients == null)
            ingridients = new ArrayList<>();
        ingridients.add(ingridientId);
    }

    public List<Ingridient> getIngridients(Context context) {
        List<Ingridient> result = new ArrayList<>();
        for (String temp : ingridients)
            result.add(DbCache.getInstance(context).getIngridientById(temp));
        return result;
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO " +
                "COMPOUND_NOMENCLATURE(" +
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
        sql = "";
        for (String ingId : ingridients) {
            sql += "INSERT OR REPLACE INTO COMPOUND_NOMENCLATURE_AND_INGRIDIENT (compound_nomenclature_id, ingridient_id) " +
                    "VALUES(" + id + ", " + ingId + ");";
        }
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void delete(Context context) {
        String sql = "DELETE FROM COMPOUND_NOMENCLATURE " +
                "WHERE id = " + id;
        DatabaseManager.getInstance(context).execSQL(sql);
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void createTable(Context context) {
        String createCompoundNomenclature = "CREATE TABLE IF NOT EXISTS " +
                "COMPOUND_NOMENCLATURE (" +
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
        DatabaseManager.getInstance(context).execSQL(createCompoundNomenclature);
        String createLinkWithIngridient = "CREATE TABLE IF NOT EXISTS " +
                "COMPOUND_NOMENCLATURE_AND_INGRIDIENT(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "compound_nomenclature_id TEXT NOT NULL, " +
                        "ingridient_id TEXT NOT NULL," +
                        "FOREIGN KEY(compound_nomenclature_id) REFERENCES COMPOUND_NOMENCLATURE(id) ON DELETE CASCADE" +
                        "FOREIGN KEY(ingridient_id) REFERENCES INGRIDIENT(id) ON DELETE CASCADE" +
                ");";
        DatabaseManager.getInstance(context).execSQL(createLinkWithIngridient);
    }

    public static List<CompoundNomenclature> getAll(Context context) {
        return DbCache.getInstance(context).getCompoundNomenclatures();
    }

    public static CompoundNomenclature getCompoundNomenclatureById(Context context, String id) {
        return DbCache.getInstance(context).getCompoundNomenclatureById(id);
    }
}
