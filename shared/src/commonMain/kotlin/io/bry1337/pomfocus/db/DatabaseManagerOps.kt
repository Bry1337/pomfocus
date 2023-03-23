package io.bry1337.pomfocus.db

import kotlinx.coroutines.flow.Flow

/**
 * Created by Bryan on 3/23/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

interface DatabaseManagerOps<Any> {
    fun listObjects(pageSize: Long = 100): Flow<List<Any>>

    fun getObject(id: String): Flow<Any>

    suspend fun saveObject(task: Any)

    suspend fun saveObjects(tasks: List<Any>)

    suspend fun deleteObject(id: String)
}
