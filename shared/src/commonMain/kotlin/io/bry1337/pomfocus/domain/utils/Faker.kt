package io.bry1337.pomfocus.domain.utils

import io.bry1337.pomfocus.domain.utils.generator.TaskGenerator

/**
 * Created by Bryan on 3/16/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

object Faker {
    val task by lazy { TaskGenerator() }
}
