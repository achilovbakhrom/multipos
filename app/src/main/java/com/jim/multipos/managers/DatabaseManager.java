package com.jim.multipos.managers;

import android.content.Context;

import com.jim.multipos.app.MultiPosApp;
import com.jim.multipos.entity.Contact;
import com.jim.multipos.entity.ContactDao;
import com.jim.multipos.entity.DaoSession;
import com.jim.multipos.utils.Constants;
import com.jim.multipos.utils.database.DatabaseHelper;
import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by Developer on 5/13/17.
 */

public class DatabaseManager {
    @Inject
    DaoSession daoSession;
    private ContactDao contactDao;
    private DatabaseHelper helper;
    private static DatabaseManager manager;
    private Context context;

    public static DatabaseManager getInstance(Context context) {
        if (manager == null) {
            manager = new DatabaseManager(context);
        }
        return manager;
    }

    @Inject
    public DatabaseManager(Context context) {
        this.context = context;
        ((MultiPosApp) context.getApplicationContext()).getBaseAppComponent().inject(this);
        contactDao = daoSession.getContactDao();
        helper = new DatabaseHelper(context, Constants.DB_NAME, 1);
        initPragmas();
    }

    public int insertToContact(Contact contact) {
        Query<Contact> contactQuery = contactDao.queryBuilder()
                .where(ContactDao.Properties.ContactValue.eq(contact.getContactValue())).build();
        if (!contactQuery.list().isEmpty())
            return Constants.SUCH_NAME_ALREADY_EXISTS;
        contactDao.insertOrReplace(contact);
        return Constants.SAVED_SUCCESSFUL;
    }

    public void deleteContact(String contactID) {
        for (Contact contact1 : contactDao.loadAll()) {
            if (contact1.getId().matches(contactID))
                contactDao.delete(contact1);
        }
    }

    private void initPragmas() {
        execSQL("PRAGMA foreign_keys = ON");
    }

    public void beginTransaction() {
        helper.getDatabase().beginTransaction();
    }

    public void commitTransaction() {
        helper.getDatabase().commitTransaction();
    }

    public SQLiteCursor query(String sql) {
        SQLiteCursor cursor = null;
        try {
            cursor = helper.query(sql);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public SQLiteCursor query(String sql, Object[] args) {
        SQLiteCursor cursor = null;
        try {
            cursor = helper.query(sql, args);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public void execSQL(String sql) {
        try {
            helper.getDatabase().execSQL(sql);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


}
