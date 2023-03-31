package io.bry1337.pomfocus.db

import com.squareup.sqldelight.runtime.coroutines.asFlow
import io.bry1337.pomfocus.domain.models.Task
import io.bry1337.pomfocus.shared.db.Database
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Created by Bryan on 3/22/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

class TaskOpsImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) :
    DatabaseModelOps<Task> {
    private val db = Database.shared
    private val scope = CoroutineScope(SupervisorJob() + dispatcher)

    /**
     * Returns a list of Tasks from the Db.
     *
     * The process is queried from the Db then the model is mapped from the db is then
     * mapped to a local model for application view and usage
     * then return it as flow
     */
    override fun listObjects(pageSize: Long): Flow<List<Task>> {
        val taskList: ArrayList<Task> = ArrayList()
        return db.pomfocusQueries.listAllTasks().asFlow().map {
            it.executeAsList()
        }.map {
            it.asFlow().map { dbTask ->
                taskList.toMutableList().also {
                        list ->
                    list.add(Task.build(dbTask.id, dbTask.description, dbTask.datetime!!))
                }
            }
        }.flatMapLatest {
            it
        }
    }

    override fun getObject(id: String): Flow<Task> {
        return db.pomfocusQueries.getTask(id = id).asFlow().map {
            it.executeAsOne()
        }.flatMapLatest { dbTask ->
            flowOf(Task.build(dbTask.id, dbTask.description, dbTask.datetime!!))
        }
    }

    override suspend fun saveObjects(tasks: List<Task>) {
        withContext(scope.coroutineContext) {
            tasks.asFlow().map {
                it
            }.collect { task ->
                if (task.id != "0") {
                    val dbTask = Task.buildDbTask(task.id, task.description, task.dateTime)
                    db.pomfocusQueries.insertTask(
                        id = dbTask.id,
                        description = dbTask.description,
                        datetime = dbTask.datetime
                    )
                }
            }
        }
    }

    override suspend fun saveObject(task: Task) {
        withContext(scope.coroutineContext) {
            db.pomfocusQueries.insertTask(
                id = task.id,
                description = task.description,
                datetime = task.dateTime
            )
        }
    }

    override suspend fun deleteObject(id: String) {
        withContext(scope.coroutineContext) {
            db.pomfocusQueries.removeTask(id = id)
        }
    }
}
