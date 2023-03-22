package io.bry1337.pomfocus.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOne
import io.bry1337.pomfocus.shared.db.Database
import io.bry1337.pomfocus.shared.db.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

class TaskOpsImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) : TaskOps {
    private val db = Database.shared
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    override fun listTasks(pageSize: Long): Flow<List<Task>> {
        return db.pomfocusQueries.listAllTasks().asFlow().mapToList(dispatcher)
    }

    override fun getTask(id: String): Flow<Task> {
        return db.pomfocusQueries.getTask(id = id).asFlow().mapToOne(dispatcher)
    }

    override suspend fun saveTask(task: Task) {
        withContext(scope.coroutineContext) {
            db.pomfocusQueries.insertTask(
                id = task.id,
                description = task.description,
                datetime = task.datetime
            )
        }
    }

    override suspend fun saveTasks(tasks: List<Task>) {
        withContext(scope.coroutineContext) {
            tasks.asFlow().map {
                db.pomfocusQueries.insertTask(
                    id = it.id,
                    description = it.description,
                    datetime = it.datetime
                )
            }
        }
    }

    override suspend fun deleteTask(id: String) {
        withContext(scope.coroutineContext) {
            db.pomfocusQueries.removeTask(id = id)
        }
    }
}
