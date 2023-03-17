package io.bry1337.pomfocus.domain.utils.generator

import io.bry1337.pomfocus.domain.models.Task
import io.bry1337.pomfocus.domain.utils.randomUUID
import kotlinx.datetime.Clock

/**
 * Created by Bryan on 3/16/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

class TaskGenerator internal constructor(): Generator<Task>{
    override fun build(overrides: Map<String, Any>): Task {
        val id = randomUUID()
        val description = "sample task"
        val dateTime = Clock.System.now()
        return Task.build(id = id, description = description, dateTime = dateTime)
    }

    override suspend fun save(entity: Task?): Task {
        // TODO("Not yet implemented")
        return build()
    }
}