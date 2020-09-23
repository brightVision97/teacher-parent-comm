package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Schema(description = "Set of specific reasons for being absent in school")
enum class AbsenceReason {

    TRUENCY,

    SICK,

    VACATION,

    PERSONAL,

    ;
}