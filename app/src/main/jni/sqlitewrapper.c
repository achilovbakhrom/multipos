#include <jni.h>

extern "C" {

JNIEXPORT int JNICALL Java_com_jim_multipos_utils_database_AbstractSQLite_opendb(JNIEnv *env, jobject object, jstring fileName, jstring cache, jstring v) {
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
}