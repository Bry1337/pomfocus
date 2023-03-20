package io.bry1337.pomfocus.domain.utils

import platform.Foundation.NSUUID

/**
 * Created by Bryan on 3/18/23.
 * Copyright (c) 2023 bry1337.github.io. All rights reserved.
 */

actual fun randomUUID(): String = NSUUID().UUIDString().lowercase()
