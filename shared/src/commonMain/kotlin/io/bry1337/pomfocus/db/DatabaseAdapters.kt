package io.bry1337.pomfocus.db

import com.squareup.sqldelight.ColumnAdapter
import kotlinx.datetime.Instant

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

val instantAdapter = object : ColumnAdapter<Instant, Long> {
    override fun decode(databaseValue: Long): Instant {
        return Instant.fromEpochMilliseconds(databaseValue)
    }

    override fun encode(value: Instant): Long {
        return value.toEpochMilliseconds()
    }
}
