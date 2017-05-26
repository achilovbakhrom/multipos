package com.jim.multipos.entity.nomenclature;

import android.content.Context;

import com.jim.multipos.entity.Entity;

import java.util.List;
import java.util.UUID;

public class NomenclatureClass implements Entity {
    private String id, name;
    private int sort;
    public NomenclatureClass() { id = UUID.randomUUID().toString(); }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getSort() { return sort; }
    public void setSort(int sort) { this.sort = sort; }

    @Override
    public void saveOrUpdate(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    @Override
    public void createTable(Context context) {

    }

    public static List<NomenclatureClass> getAll(Context context) {

        return null;
    }

    public static NomenclatureClass getClassById(Context context, String id) {

        return null;
    }

}
