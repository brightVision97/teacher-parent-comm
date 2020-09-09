package com.rachev.teacherparentcomm.util

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.domain.Sort

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */

fun randomstring(length: Long): String = RandomStringUtils.random(6)

internal val sort = Sort.by(Sort.Direction.DESC, "start")