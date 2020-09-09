package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Schema(description = "A specific reason for being absent in school")
enum class AbsenceReason {

    TRUENCY,

    SICK,

    BETWEEN_TERMS_BREAK,

    VACATION,

    NATIONAL_HOLIDAY,

    OTHER_PERSONAL_REASON,

    ;
}