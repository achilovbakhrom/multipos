package com.jim.multipos.entity.nomenclature.matrix;

import android.content.Context;

import com.jim.multipos.entity.Entity;
import com.jim.multipos.entity.nomenclature.AbstractNomenclature;

import java.util.List;

/**
 * Created by Developer on 5/24/17.
 */

public class MatrixNomenclature extends AbstractNomenclature implements Entity{

    private String parentNomenclatureId;
    private List<AttributeValuePair> attrs;

    public String getParentNomenclatureId() {
        return parentNomenclatureId;
    }

    public void setParentNomenclatureId(String parentNomenclatureId) {
        this.parentNomenclatureId = parentNomenclatureId;
    }

    public List<AttributeValuePair> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttributeValuePair> attrs) {
        this.attrs = attrs;
    }

    @Override
    public boolean isSellable() {
        return true;
    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public void saveOrUpdate(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    @Override
    public void createTable(Context context) {

    }

}
