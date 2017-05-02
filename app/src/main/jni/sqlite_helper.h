//
// Created by Developer on 4/25/17.
//
#include <stdio.h>
#include "sqlite/sqlite3.h"

#ifndef SQLITENATIVE2_SQLITE_HELPER_H
#define SQLITENATIVE2_SQLITE_HELPER_H

#ifdef __cplusplus
extern "C" {
#endif

int create_db();
int insert_data();
int select_data();
char* get_data();
#ifdef __cplusplus
}
#endif



#endif //SQLITENATIVE2_SQLITE_HELPER_H
