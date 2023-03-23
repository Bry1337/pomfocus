package io.bry1337.pomfocus.domain.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import io.bry1337.pomfocus.shared.db.Task as DbTask

/**
 * Created by Bryan on 3/16/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

@Serializable
data class Task(
    val id: String,
    val description: String,
    val dateTime: Instant
) {
    companion object {
        fun build(id: String, description: String, dateTime: Instant): Task {
            return Task(id, description, dateTime)
        }

        fun buildEmpty(): Task {
            return Task("0", "", Clock.System.now())
        }

        fun buildTask(id: String, description: String, dateTime: Instant): DbTask {
            return DbTask(id = id, description = description, datetime = dateTime)
        }
    }
}
