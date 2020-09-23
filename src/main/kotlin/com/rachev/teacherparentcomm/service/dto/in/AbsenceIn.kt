package com.rachev.teacherparentcomm.service.dto.`in`

import com.fasterxml.jackson.annotation.JsonFormat
import com.rachev.teacherparentcomm.controller.form.AbsenceForm
import com.rachev.teacherparentcomm.repository.models.AbsenceReason
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class AbsenceIn(

    val referenceId: String?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val start: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val end: LocalDateTime,

    var reason: AbsenceReason
) {

    companion object {

        fun map(dtoIn: AbsenceForm) =
            with(dtoIn) {
                AbsenceIn(
                    referenceId = referenceId,
                    start = start,
                    end = end,
                    reason = reason
                )
            }
    }
}