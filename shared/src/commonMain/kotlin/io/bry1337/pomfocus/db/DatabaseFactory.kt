package io.bry1337.pomfocus.db

import com.squareup.sqldelight.db.SqlDriver
import io.bry1337.pomfocus.shared.db.Database
import io.bry1337.pomfocus.shared.db.Task

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

const val ENABLE_FOREIGN_KEY = "PRAGMA foreign_keys = ON"

// Reference:
// https://kotlinlang.org/docs/multiplatform-mobile-ktor-sqldelight.html
expect fun createSqlDriver(schema: SqlDriver.Schema, name: String): SqlDriver

// Extension field for database to provide the database name
val Database.Companion.databaseName: String
    get() = "pomfocus.db"

// Extension field for database to provide an instance of database
val Database.Companion.shared: Database by lazy {
    Database(
        driver = createSqlDriver(Database.Schema, Database.databaseName),
        taskAdapter = Task.Adapter(datetimeAdapter = instantAdapter)
    )
}
