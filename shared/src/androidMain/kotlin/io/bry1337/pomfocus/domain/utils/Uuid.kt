package io.bry1337.pomfocus.domain.utils

import java.util.UUID

/**
 * Created by Bryan on 3/16/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

actual fun randomUUID() = UUID.randomUUID().toString().lowercase()