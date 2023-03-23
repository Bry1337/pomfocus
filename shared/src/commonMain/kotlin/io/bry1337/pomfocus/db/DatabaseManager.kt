package io.bry1337.pomfocus.db

import kotlinx.coroutines.flow.Flow

/**
 * Created by Bryan on 3/23/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

class DatabaseManager constructor(private val managerOps: DatabaseModelOps<Any>) :
    DatabaseManagerOps<Any> {
    override fun listObjects(pageSize: Long): Flow<List<Any>> {
        return managerOps.listObjects()
    }

    override fun getObject(id: String): Flow<Any> {
        return managerOps.getObject(id)
    }

    override suspend fun deleteObject(id: String) {
        managerOps.deleteObject(id)
    }

    override suspend fun saveObjects(tasks: List<Any>) {
        managerOps.saveObjects(tasks)
    }

    override suspend fun saveObject(task: Any) {
        managerOps.saveObject(task)
    }
}
