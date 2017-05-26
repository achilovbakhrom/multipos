package com.jim.multipos.entity.nomenclature.matrix;

import android.content.Context;

import com.google.gson.Gson;
import com.jim.multipos.entity.DbCache;
import com.jim.multipos.entity.Entity;
import com.jim.multipos.entity.nomenclature.Nomenclature;
import com.jim.multipos.managers.DatabaseManager;
import com.jim.multipos.utils.CommonUtils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Developer on 5/23/17.
 */

public class Matrix implements Entity{
    private String id;
    private String mainNomenclatureId;
    private List<Attribute> attributes;

    public Matrix() {
        id = UUID.randomUUID().toString();
    }

    @Override
    public void saveOrUpdate(Context context) {
        createTable(context);
        String sql = "INSERT OR REPLACE INTO MATRIX(id, main_nomenclature_id) VALUES (" + id + ", " + mainNomenclatureId + ");";
        for (Attribute attr : attributes) {
            sql += "INSERT OR REPLACE INTO ATTRIBUTE_AND_MATRIX(matrix_id, attribute_id) VALUES (" + id + ", " + attr.getId() + ");";
        }
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void delete(Context context) {
        String sql = "DELETE FROM MATRIX WHERE id = " + id + ";";
        DatabaseManager.getInstance(context).execSQL(sql);
        DbCache.getInstance(context).refresh(getClass());
    }

    @Override
    public void createTable(Context context) {
        String sql = "CREATE TABLE IF NOT EXISTS MATRIX(" +
                "id TEXT PRIMARY KEY NOT NULL," +
                "main_nomenclature_id TEXT NOT NULL," +
                "FOREIGN KEY(main_nomenclature_id) REFERENCES NOMENCLATURE(id) ON UPDATE CASCADE ON DELETE CASCADE);";

        sql += "CREATE TABLE IF NOT EXISTS MATRIX_AND_ATTRIBUTE(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "matrix_id TEXT NOT NULL," +
                "attribute_id TEXT NOT NULL," +
                "FOREIGN KEY(matrix_id) REFERENCES MATRIX(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "FOREIGN KEY(attribute_id) REFERENCES ATTRIBUTE(id) ON DELETE CASCADE ON UPDATE CASCADE);";
        DatabaseManager.getInstance(context).beginTransaction();
        DatabaseManager.getInstance(context).execSQL(sql);
        DatabaseManager.getInstance(context).commitTransaction();
    }

    public List<MatrixNomenclature> autoGenerateNomenclatures(Context context) {
        Nomenclature main = Nomenclature.getNomenclatureById(context, mainNomenclatureId);
        List<MatrixNomenclature> nomenclatures = new ArrayList<>();
        List<List<AttributeValuePair>> pairs = combinationOfAttributeAndValues(attributes);
        for (List<AttributeValuePair> temp : pairs) {
            MatrixNomenclature matrixNomenclature = new MatrixNomenclature();
            matrixNomenclature.setActive(true);
            matrixNomenclature.setName(main.getName());
            matrixNomenclature.setDescription(main.getDescription());
            matrixNomenclature.setBarCode(main.getBarCode());
            matrixNomenclature.setSku(main.getSku());
            matrixNomenclature.setCrossCode(main.getCrossCode());
            matrixNomenclature.setGroupId(main.getGroupId());
            matrixNomenclature.setPhotoPath(main.getPhotoPath());
            matrixNomenclature.setClassId(main.getClassId());
            matrixNomenclature.setCategoryId(main.getCategoryId());
            matrixNomenclature.setVendorId(main.getVendorId());
            matrixNomenclature.setMinCount(main.getMinCount());
            matrixNomenclature.setCost(main.getCost());
            matrixNomenclature.setPrice(main.getPrice());
            matrixNomenclature.setParentNomenclatureId(mainNomenclatureId);
            matrixNomenclature.setAttrs(temp);
            nomenclatures.add(matrixNomenclature);
        }
        return nomenclatures;
    }

    private List<List<AttributeValuePair>> combinationOfAttributeAndValues(List<Attribute> attributes) {
        List<AttributeValuePair> tempPairs = new ArrayList<>();
        for (Attribute attr : attributes) {
            for (String value : attr.getValues()) {
                AttributeValuePair pair = new AttributeValuePair();
                pair.setAttribute(attr);
                pair.setValue(value);
                tempPairs.add(pair);
            }
        }
        List<List<AttributeValuePair>> comb = CommonUtils.combination(tempPairs);
        for (int i = 0; i < comb.size(); i++) {
            for (int j = i + 1; j < comb.size(); j++) {
                boolean different = false;
                for (int k = 0; k < comb.get(i).size(); k++) {
                    if (!comb.get(j).contains(comb.get(i).get(k))) {
                        different = true;
                    }
                }
                if (different) {
                    comb.remove(j);
                    j--;
                }
            }
        }
        return comb;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Matrix createFromJson(String json) {
        return new Gson().fromJson(json, Matrix.class);
    }

    public static Matrix getMatrixById(Context context, String id) {
        return DbCache.getInstance(context).getMatrixById(id);
    }

    public static List<Matrix> getAll(Context context) {
        return DbCache.getInstance(context).getMatrices();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainNomenclatureId() {
        return mainNomenclatureId;
    }

    public void setMainNomenclatureId(String mainNomenclatureId) {
        this.mainNomenclatureId = mainNomenclatureId;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }


}
