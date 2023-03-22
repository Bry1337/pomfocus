package io.bry1337.pomfocus.db

import io.bry1337.pomfocus.shared.db.Task
import kotlinx.coroutines.flow.Flow

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface TaskOps {

    fun listTasks(pageSize: Long = 100): Flow<List<Task>>

    fun getTask(id: String): Flow<Task>

    suspend fun saveTask(task: Task)

    suspend fun saveTasks(tasks: List<Task>)

    suspend fun deleteTask(id: String)
}
