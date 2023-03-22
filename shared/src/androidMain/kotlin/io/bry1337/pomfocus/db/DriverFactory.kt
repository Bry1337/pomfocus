package io.bry1337.pomfocus.db

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.bry1337.pomfocus.theme.AppContext

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

actual fun createSqlDriver(schema: SqlDriver.Schema, name: String): SqlDriver =
    AndroidSqliteDriver(
        schema,
        AppContext.getContext() as Context,
        name,
        callback = object : AndroidSqliteDriver.Callback(schema) {
            override fun onOpen(db: SupportSQLiteDatabase) {
                db.execSQL(ENABLE_FOREIGN_KEY)
            }
        }
    )
