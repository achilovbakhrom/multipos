#include <jni.h>
#include <android/log.h>
#include "sqlite/sqlite3.h"
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <sstream>
#include <iostream>
#include <string>

#define TAG "com.jim.multipos"

#include <string>
#define DB_PATH "/data/data/com.jim.multipos/databases"


void throw_sqlite3_exception(JNIEnv *env, sqlite3 *handle, int errcode) {

    if (SQLITE_OK == errcode) {
        errcode = sqlite3_errcode(handle);
    }
    const char *errmsg = sqlite3_errmsg(handle);
    jclass exClass = env->FindClass("com/jim/multipos/orm/database/SQLiteException");
    env->ThrowNew(exClass, errmsg);
}

extern "C" {


int Java_com_jim_multipos_utils_database_SQLitePreparedStatement_step(JNIEnv *env, jobject object, int statementHandle) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;
    int errcode = sqlite3_step(handle);
    if (errcode == SQLITE_ROW)  {
        return 0;
    } else if(errcode == SQLITE_DONE) {
        return 1;
    }  else if(errcode == SQLITE_BUSY) {
        return -1;
    }
    throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);

}

int Java_com_jim_multipos_utils_database_SQLitePreparedStatement_prepare(JNIEnv *env, jobject object, int sqliteHandle, jstring sql) {
    sqlite3 *handle = (sqlite3 *) sqliteHandle;

    char const *sqlStr = env->GetStringUTFChars(sql, 0);

    sqlite3_stmt *stmt_handle;

    int errcode = sqlite3_prepare_v2(handle, sqlStr, -1, &stmt_handle, 0);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, handle, errcode);
    }

    if (sqlStr != 0) {
        env->ReleaseStringUTFChars(sql, sqlStr);
    }

    return (int) stmt_handle;
}

void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_reset(JNIEnv *env, jobject object, int statementHandle) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;
    int errcode = sqlite3_reset(handle);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }
}

void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_finalize(JNIEnv *env, jobject object, int statementHandle) {
    sqlite3_finalize((sqlite3_stmt *) statementHandle);
}

//binding none primitive types
void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_bindByteBuffer(JNIEnv *env, jobject object, int statementHandle, int index, jobject value, int length) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;
    void *buf = env->GetDirectBufferAddress(value);

    int errcode = sqlite3_bind_blob(handle, index, buf, length, SQLITE_STATIC);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }
}

//binding string
void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_bindString(JNIEnv *env, jobject object, int statementHandle, int index, jstring value) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;
    char const *valueStr = env->GetStringUTFChars(value, 0);
    int errcode = sqlite3_bind_text(handle, index, valueStr, -1, SQLITE_TRANSIENT);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }

    if (valueStr != 0) {
        env->ReleaseStringUTFChars(value, valueStr);
    }
}

//bind int
void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_bindInt(JNIEnv *env, jobject object, int statementHandle, int index, int value) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;

    int errcode = sqlite3_bind_int(handle, index, value);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }
}

//bind long
void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_bindLong(JNIEnv *env, jobject object, int statementHandle, int index, long long value) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;

    int errcode = sqlite3_bind_int64(handle, index, value);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }
}

//bind Double
void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_bindDouble(JNIEnv *env, jobject object, int statementHandle, int index, double value) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;

    int errcode = sqlite3_bind_double(handle, index, value);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }
}

//bind Null
void Java_com_jim_multipos_utils_database_SQLitePreparedStatement_bindNull(JNIEnv *env, jobject object, int statementHandle, int index) {
    sqlite3_stmt *handle = (sqlite3_stmt *) statementHandle;

    int errcode = sqlite3_bind_null(handle, index);
    if (SQLITE_OK != errcode) {
        throw_sqlite3_exception(env, sqlite3_db_handle(handle), errcode);
    }
}

//close db
void Java_com_jim_multipos_utils_database_AbstractSQLite_closedb(JNIEnv *env, jobject object, int sqliteHandle) {
    sqlite3 *handle = (sqlite3 *)sqliteHandle;
    int err = sqlite3_close(handle);
    if (SQLITE_OK != err) {
        throw_sqlite3_exception(env, handle, err);
    }
}

//begin transaction
void Java_com_jim_multipos_utils_database_AbstractSQLite_beginTransaction(JNIEnv *env, jobject object, int sqliteHandle) {
    sqlite3 *handle = (sqlite3 *)sqliteHandle;
    sqlite3_exec(handle, "BEGIN", 0, 0, 0);
}

//commit transaction
void Java_com_jim_multipos_utils_database_AbstractSQLite_commitTransaction(JNIEnv *env, jobject object, int sqliteHandle) {
    sqlite3 *handle = (sqlite3 *)sqliteHandle;
    sqlite3_exec(handle, "COMMIT", 0, 0, 0);
}

//open existing db
int Java_com_jim_multipos_utils_database_AbstractSQLite_opendb(JNIEnv *env, jobject object, jstring fileName, jstring cache, jstring v) {
    char const *version = env->GetStringUTFChars(v, 0);
    char const *fileNameStr = env->GetStringUTFChars(fileName, 0);
    char const *cacheDir = env->GetStringUTFChars(cache, 0);
    if (sqlite3_temp_directory != 0) {
        sqlite3_free(sqlite3_temp_directory);
    }
    sqlite3_temp_directory = sqlite3_mprintf("%s", cacheDir);

    sqlite3 *handle = 0;

    int err = sqlite3_open(fileNameStr, &handle);
    if (SQLITE_OK != err) {
        throw_sqlite3_exception(env, handle, err);
    }

    if (fileNameStr != 0) {
        env->ReleaseStringUTFChars(fileName, fileNameStr);
        env->ReleaseStringUTFChars(v, version);
        env->ReleaseStringUTFChars(cache, cacheDir);
    }

    sqlite3_stmt *stmt;
    std::string sql = "PRAGMA schema_version;";
    char temp_query[100];
    strcpy(temp_query, sql.c_str());
    int rc = sqlite3_prepare_v2(handle, temp_query, sizeof(temp_query), &stmt, NULL);
    bool changed = false;
    int currentVersion;
    if ( rc ) {
        __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", "Can't prepare statement...");
    }
    else {
        __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", "Prepared successfull");
        if (sqlite3_step(stmt) == SQLITE_ROW) {
            currentVersion = sqlite3_column_int(stmt, 0);
            std::string temp = std::to_string(currentVersion);
            __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", std::to_string(currentVersion).c_str());
            changed = std::operator!=(temp, version);
        }
    }
    sqlite3_finalize(stmt);
    if (changed) {
        __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", "Version is changed ... ");
        std::string query = "PRAGMA schema_version=";
        query += version;
        query += ";";
        char temp_q[100];
        strcpy(temp_q, query.c_str());
        rc = sqlite3_prepare_v2(handle, temp_q, sizeof(temp_q), &stmt, NULL);
        if ( rc ) {
            __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", "Can't prepare statement...");
            throw_sqlite3_exception(env, handle, (int) handle);
        }
        else {
            __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", "step");
            sqlite3_step(stmt);
        }

        sqlite3_finalize(stmt);

        jclass objClass = env->GetObjectClass(object);
        int temp = std::stoi(version);
        jmethodID method = env->GetMethodID(objClass, "versionChangeNotification", "(II)V");
        env->CallVoidMethod(object, method, currentVersion, temp);
    }
    return (int)handle;
}

int Java_com_jim_multipos_utils_database_SQLiteCursor_columnType(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    return sqlite3_column_type(handle, columnIndex);
}

int Java_com_jim_multipos_utils_database_SQLiteCursor_columnIsNull(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    int valType = sqlite3_column_type(handle, columnIndex);
    return SQLITE_NULL == valType;
}

int Java_com_jim_multipos_utils_database_SQLiteCursor_columnIntValue(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    int valType = sqlite3_column_type(handle, columnIndex);
    if (SQLITE_NULL == valType) {
        return 0;
    }
    return sqlite3_column_int(handle, columnIndex);
}

long long Java_com_jim_multipos_utils_database_SQLiteCursor_columnLongValue(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    int valType = sqlite3_column_type(handle, columnIndex);
    if (SQLITE_NULL == valType) {
        return 0;
    }
    return sqlite3_column_int64(handle, columnIndex);
}

double Java_com_jim_multipos_utils_database_SQLiteCursor_columnDoubleValue(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    int valType = sqlite3_column_type(handle, columnIndex);
    if (SQLITE_NULL == valType) {
        return 0;
    }
    return sqlite3_column_double(handle, columnIndex);
}

jstring Java_com_jim_multipos_utils_database_SQLiteCursor_columnStringValue(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    const char *str = (const char *) sqlite3_column_text(handle, columnIndex);
    if (str != 0) {
        return env->NewStringUTF(str);
    }
    return 0;
}

int Java_com_jim_multipos_utils_database_SQLiteCursor_columnsCount(JNIEnv *env, jobject object, int statementHandle) {
    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    return sqlite3_column_count(handle);
}

//array
jbyteArray Java_com_jim_multipos_utils_database_SQLiteCursor_columnByteArrayValue(JNIEnv *env, jobject object, int statementHandle, int columnIndex) {

    sqlite3_stmt *handle = (sqlite3_stmt *)statementHandle;
    const jbyte *buf = (const jbyte *) sqlite3_column_blob(handle, columnIndex);
    int length = sqlite3_column_bytes(handle, columnIndex);
    if (buf != nullptr && length > 0) {
        jbyteArray result = env->NewByteArray(length);
        env->SetByteArrayRegion(result, 0, length, buf);
        return result;
    }
    return nullptr;
}

int Java_com_jim_multipos_utils_database_AbstractSQLite_columnIndex(JNIEnv *env, jobject object, int dbHandle, jstring tableName, jstring columnName) {
    sqlite3 *db = (sqlite3 *) dbHandle;
    sqlite3_stmt *handle;
    int colIndex = 0, colName = 1, colType = 2;
    char const *tn = env->GetStringUTFChars(tableName, 0);
    char const *cn = env->GetStringUTFChars(columnName, 0);
    char pragmaQuery[100] = "PRAGMA table_info(";
    strcat(pragmaQuery, tn);
    int rc = sqlite3_prepare_v2(db, pragmaQuery, sizeof(pragmaQuery), &handle, NULL);
    if (rc != SQLITE_OK) {
        throw_sqlite3_exception(env, db, rc);
        return -1;
    }
    while (sqlite3_step(handle) == SQLITE_ROW) {
        const char *str = (const char *) sqlite3_column_text(handle, colName);
        if (strcmp(str, cn) == 0) {
            if (tn != 0) {
                env->ReleaseStringUTFChars(tableName, tn);
                env->ReleaseStringUTFChars(columnName, cn);
            }
            return sqlite3_column_int(handle, colIndex);
        }
    }
    if (tn != 0) {
        env->ReleaseStringUTFChars(tableName, tn);
        env->ReleaseStringUTFChars(columnName, cn);
    }
    throw_sqlite3_exception(env, db, rc);
    return -1;
}

void Java_com_jim_multipos_utils_database_AbstractSQLite_execSQL(JNIEnv *env, jobject object, int dbHandle, jstring sql) {
    sqlite3 *db = (sqlite3 *) dbHandle;
    char const *query = env->GetStringUTFChars(sql, 0);
    int rc = sqlite3_exec(db, query, NULL, 0, 0);
    if (rc != SQLITE_OK) {
        throw_sqlite3_exception(env, db, rc);
        return;
    }
    if (query != 0) {
        env->ReleaseStringUTFChars(sql, query);
    }
}

void Java_com_jim_multipos_utils_database_AbstractSQLite_tableinfo(JNIEnv *env, jobject object, int dbHandle, jstring tableName) {
    sqlite3 *db = (sqlite3 *) dbHandle;
    sqlite3_stmt *handle;
    char const *tn = env->GetStringUTFChars(tableName, 0);
    char test[100] = "CREATE TABLE IF NOT EXISTS foo (id INTEGER, name TEXT, test TEXT);";
    int rc = sqlite3_exec(db, test, NULL, 0, 0);
    if (rc != SQLITE_OK) {
        throw_sqlite3_exception(env, db, rc);
        return;
    }
    else
        __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", "table created");

    std::string temp =  "PRAGMA table_info(foo)";

    char query[100];
    strcpy(query, temp.c_str());
    rc = sqlite3_prepare_v2(db, query, sizeof(query), &handle, NULL);
    if (rc != SQLITE_OK) {
        throw_sqlite3_exception(env, db, rc);
        return;
    }

    while (sqlite3_step(handle) == SQLITE_ROW) {
        __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", std::to_string(sqlite3_column_count(handle)).c_str());
        const char *str = (const char *) sqlite3_column_text(handle, 0);
        __android_log_write(ANDROID_LOG_DEBUG, "com.jim.multipos", str);
    }

    sqlite3_finalize(handle);
    if (tn != 0) {
        env->ReleaseStringUTFChars(tableName, tn);
    }
}



}



