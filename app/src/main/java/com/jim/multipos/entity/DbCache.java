package com.jim.multipos.entity;

import android.content.Context;


import com.jim.multipos.entity.nomenclature.Nomenclature;
import com.jim.multipos.entity.nomenclature.combo.ComboNomenclature;
import com.jim.multipos.entity.nomenclature.combo.Slot;
import com.jim.multipos.entity.nomenclature.ingridient.CompoundNomenclature;
import com.jim.multipos.entity.nomenclature.ingridient.Ingridient;
import com.jim.multipos.entity.nomenclature.matrix.Attribute;
import com.jim.multipos.entity.nomenclature.matrix.Matrix;
import com.jim.multipos.utils.managers.DatabaseManager;
import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Developer on 5/24/17.
 */

public class DbCache {

    private static DbCache instance;

    private List<CompoundNomenclature> compoundNomenclatures;
    private List<Ingridient> ingridients;
    private List<Attribute> attributes;
    private List<Matrix> matrices;
    private List<Nomenclature> nomenclatures;
    private List<ComboNomenclature> comboNomenclatures;
    private List<Slot> slots;
    public static DbCache getInstance(Context context) {
        if (instance == null) instance = new DbCache(context);
        return instance;
    }

    private Context context;
    private DbCache(Context context) {
        this.context = context;
    }

    /**
     * Collecting all CompoundNomenclatures
     * @return compoundNomenclatures or empty List<CompoundNomenclature>();
     */
    public List<CompoundNomenclature> getCompoundNomenclatures() {
        if (compoundNomenclatures == null) {
            compoundNomenclatures = loadCompoundNomenclatures();
        }
        return compoundNomenclatures;
    }

    /**
     * refreshing cache
     * @param cl - clears and reloads represent collection by this param
     */
    public void refresh(Class cl) {
        if (cl.getName().equals(CompoundNomenclature.class.getName())) {
            compoundNomenclatures = null;
        } else if (cl.getName().equals(Ingridient.class.getName())) {
            ingridients = null;
        }
    }

    /**
     * returns CompoundNomenclature by given id from db
     * @param id given id of finding CompoundNomenclature
     * @return CompoundNomenclature
     */
    public CompoundNomenclature getCompoundNomenclatureById(final String id) {
        CompoundNomenclature result = null;
        if (compoundNomenclatures == null) {
            compoundNomenclatures = loadCompoundNomenclatures();
        }
        result = CollectionUtils.find(compoundNomenclatures, new org.apache.commons.collections4.Predicate<CompoundNomenclature>() {
            @Override
            public boolean evaluate(CompoundNomenclature object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    /**
     * @return all Ingridients from db
     */
    public List<Ingridient> getIngridients() {
        if (ingridients == null)
            ingridients = loadIngridients();
        return  ingridients;
    }

    /**
     * Returns all ingridients from db
     * @param id by given param
     * @return all ingridients from db
     */
    public Ingridient getIngridientById(final String id) {
        Ingridient result = null;
        if (ingridients == null) {
            ingridients = loadIngridients();
        }
        result = CollectionUtils.find(ingridients, new Predicate<Ingridient>() {
            @Override
            public boolean evaluate(Ingridient object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    public List<Attribute> getAttributes() {
        if (attributes == null)
            attributes = loadAttributes();
        return attributes;
    }

    public Attribute getAttributeById(final String id) {
        Attribute result = null;
        if (attributes == null)
            attributes = loadAttributes();
        result = CollectionUtils.find(attributes, new Predicate<Attribute>() {
            @Override
            public boolean evaluate(Attribute object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    public List<Matrix> getMatrices() {
        if (matrices == null)
            matrices = loadMatrices();
        return matrices;
    }

    public Matrix getMatrixById(final String id) {
        Matrix result = null;
        if (matrices == null)
            matrices = loadMatrices();
        result = CollectionUtils.find(matrices, new Predicate<Matrix>() {
            @Override
            public boolean evaluate(Matrix object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    public List<Nomenclature> getNomenclatures() {
        if (nomenclatures == null)
            nomenclatures = loadNomenclatures();
        return nomenclatures;
    }

    public Nomenclature getNomenclatureById(final String id) {
        Nomenclature result = null;
        if (nomenclatures == null)
            nomenclatures = loadNomenclatures();
        result = CollectionUtils.find(nomenclatures, new Predicate<Nomenclature>() {
            @Override
            public boolean evaluate(Nomenclature object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    public ComboNomenclature getComboNomenclatureById(final String id) {
        ComboNomenclature result = null;
        if (comboNomenclatures == null)
            comboNomenclatures = loadComboNomenclatures();
        result = CollectionUtils.find(comboNomenclatures, new Predicate<ComboNomenclature>() {
            @Override
            public boolean evaluate(ComboNomenclature object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    public List<ComboNomenclature> getComboNomenclatures() {
        if (comboNomenclatures == null)
            comboNomenclatures = loadComboNomenclatures();
        return comboNomenclatures;
    }

    public Slot getSlotById(final String id) {
        Slot result = null;
        if (slots == null)
            slots = loadSlots();
        result = CollectionUtils.find(slots, new Predicate<Slot>() {
            @Override
            public boolean evaluate(Slot object) {
                return object.getId().equals(id);
            }
        });
        return result;
    }

    public List<Slot> getSlots() {
        if (slots == null)
            slots = loadSlots();
        return slots;
    }

    /**
     * @return loads all ingridients from db
     */
    private List<Ingridient> loadIngridients() {
        List<Ingridient> result = new ArrayList<>();
        String sql = "SELECT * FROM INGRIDIENT;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                Ingridient ingridient = new Ingridient();
                ingridient.setId(cursor.stringValue(0));
                ingridient.setName(cursor.stringValue(1));
                ingridient.setDescription(cursor.stringValue(2));
                ingridient.setPhotoPath(cursor.stringValue(3));
                ingridient.setGroupId(cursor.stringValue(4));
                ingridient.setClassId(cursor.stringValue(5));
                ingridient.setCategoryId(cursor.stringValue(6));
                ingridient.setVendorId(cursor.stringValue(7));
                ingridient.setMinCount(cursor.intValue(8));
                ingridient.setSku(cursor.longValue(9));
                ingridient.setBarCode(cursor.longValue(10));
                ingridient.setCrossCode(cursor.longValue(11));
                ingridient.setCost(cursor.doubleValue(12));
                ingridient.setPrice(cursor.doubleValue(13));
                ingridient.setActive(cursor.intValue(14) != 0);
                result.add(ingridient);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @return all CompoundNomenclatures from db
     */
    private List<CompoundNomenclature> loadCompoundNomenclatures() {
        List<CompoundNomenclature> result = new ArrayList<>();
        String sql = "SELECT * FROM COMPOUND_NOMENCLATURE;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                CompoundNomenclature nomenclature = new CompoundNomenclature();
                String id = cursor.stringValue(0);
                nomenclature.setId(id);
                nomenclature.setName(cursor.stringValue(1));
                nomenclature.setDescription(cursor.stringValue(2));
                nomenclature.setPhotoPath(cursor.stringValue(3));
                nomenclature.setGroupId(cursor.stringValue(4));
                nomenclature.setClassId(cursor.stringValue(5));
                nomenclature.setCategoryId(cursor.stringValue(6));
                nomenclature.setVendorId(cursor.stringValue(7));
                nomenclature.setMinCount(cursor.intValue(8));
                nomenclature.setSku(cursor.longValue(9));
                nomenclature.setBarCode(cursor.longValue(10));
                nomenclature.setCrossCode(cursor.longValue(11));
                nomenclature.setCost(cursor.doubleValue(12));
                nomenclature.setPrice(cursor.doubleValue(13));
                nomenclature.setActive(cursor.intValue(14) != 0);
                String temp = "SELECT ingridient_id FROM COMPOUND_NOMENCLATURE_AND_INGRIDIENT WHERE compound_nomenclature_id = ?";
                SQLiteCursor c = DatabaseManager.getInstance(context).query(temp, new Object[] {id});
                while (c.next()) {
                    nomenclature.addIngridient(c.stringValue(2));
                }
                result.add(nomenclature);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Attribute> loadAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        String sql = "SELECT * FROM ATTRIBUTE;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                Attribute attribute = new Attribute();
                String id = cursor.stringValue(0);
                attribute.setId(id);
                attribute.setName(cursor.stringValue(1));
                attribute.setSort(cursor.longValue(2));
                attribute.setActive(cursor.intValue(3) != 0);
                String temp = "SELECT value FROM ATTRIBUTE_AND_VALUE WHERE attribute_id = ?";
                SQLiteCursor c = DatabaseManager.getInstance(context).query(temp, new Object[] {id});
                while (c.next()) {
                    attribute.addValue(c.stringValue(0));
                }
                attributes.add(attribute);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    private List<Matrix> loadMatrices() {
        List<Matrix> matrices = new ArrayList<>();
        String sql = "SELECT * FROM MATRIX;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                Matrix matrix = new Matrix();
                String id = cursor.stringValue(0);
                matrix.setId(id);
                matrix.setMainNomenclatureId(cursor.stringValue(1));
                List<Attribute> attributes = new ArrayList<>();
                String temp = "SELECT attribute_id FROM MATRIX_AND_ATTRIBUTE WHERE matrix_id = ?;";
                SQLiteCursor c = DatabaseManager.getInstance(context).query(temp, new Object[] { id });
                while (c.next()) {
                    Attribute attribute = Attribute.getAttributeById(context, c.stringValue(2));
                    attributes.add(attribute);
                }
                matrix.setAttributes(attributes);
                matrices.add(matrix);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return matrices;
    }

    private List<Nomenclature> loadNomenclatures() {
        List<Nomenclature> result = new ArrayList<>();
        String sql = "SELECT * FROM NOMENCLATURE;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                Nomenclature nomenclature = new Nomenclature();
                String id = cursor.stringValue(0);
                nomenclature.setId(id);
                nomenclature.setName(cursor.stringValue(1));
                nomenclature.setDescription(cursor.stringValue(2));
                nomenclature.setPhotoPath(cursor.stringValue(3));
                nomenclature.setGroupId(cursor.stringValue(4));
                nomenclature.setClassId(cursor.stringValue(5));
                nomenclature.setCategoryId(cursor.stringValue(6));
                nomenclature.setVendorId(cursor.stringValue(7));
                nomenclature.setMinCount(cursor.intValue(8));
                nomenclature.setSku(cursor.longValue(9));
                nomenclature.setBarCode(cursor.longValue(10));
                nomenclature.setCrossCode(cursor.longValue(11));
                nomenclature.setCost(cursor.doubleValue(12));
                nomenclature.setPrice(cursor.doubleValue(13));
                nomenclature.setActive(cursor.intValue(14) != 0);
                result.add(nomenclature);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<Slot> loadSlots() {
        List<Slot> result = new ArrayList<>();
        String sql = "SELECT * FROM SLOT;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                Slot slot = new Slot();
                String id = cursor.stringValue(0);
                slot.setId(id);
                slot.setName(cursor.stringValue(1));
                slot.setPhotoPath(cursor.stringValue(2));
                String sqlPrice = "SELECT nomenclature_id, margin FROM SLOT_AND_NOMENCLATURE WHERE slot_id = ?";
                SQLiteCursor c = DatabaseManager.getInstance(context).query(sqlPrice, new Object[] { id });
                Map<String, Double> items = new HashMap<>();
                while (c.next()) {
                    items.put(c.stringValue(2), c.doubleValue(3));
                }
                slot.setItems(items);
                result.add(slot);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<ComboNomenclature> loadComboNomenclatures() {
        List<ComboNomenclature> result = new ArrayList<>();
        String sql = "SELECT * FROM COMBO_NOMENCLATURE;";
        SQLiteCursor cursor = DatabaseManager.getInstance(context).query(sql);
        try {
            while (cursor.next()) {
                ComboNomenclature nomenclature = new ComboNomenclature();
                String id = cursor.stringValue(0);
                nomenclature.setId(id);
                nomenclature.setName(cursor.stringValue(1));
                nomenclature.setPrice(cursor.doubleValue(2));
                nomenclature.setPhotoPath(cursor.stringValue(3));
                String sqlComponents = "SELECT * FROM COMBO_NOMENCLATURE_AND_SLOT WHERE combo_nomenclature_id = ?";
                SQLiteCursor c = DatabaseManager.getInstance(context).query(sqlComponents, new Object[] {id});
                List<Slot> slots = new ArrayList<>();
                while (c.next()) {
                    String slotId = c.stringValue(2);
                    slots.add(Slot.getSlotById(context, slotId));
                }
                nomenclature.setComponents(slots);
                result.add(nomenclature);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void reload() {
        //TODO reload all datas in the cache
    }
}