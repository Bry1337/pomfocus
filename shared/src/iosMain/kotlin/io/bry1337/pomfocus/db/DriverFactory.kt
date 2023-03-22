package io.bry1337.pomfocus.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.bry1337.pomfocus.shared.db.Database

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

actual fun createSqlDriver(schema: SqlDriver.Schema, name: String): SqlDriver {
    val driver = NativeSqliteDriver(Database.Schema, Database.databaseName)
    driver.execute(null, ENABLE_FOREIGN_KEY, 0)
    return driver
}
