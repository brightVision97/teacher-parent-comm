package com.rachev.teacherparentcomm.service.dto.out

import com.fasterxml.jackson.annotation.JsonFormat
import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.AbsenceReason
import com.rachev.teacherparentcomm.service.dto.`in`.AbsenceIn
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class Absence(

    val referenceId: String?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val start: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val end: LocalDateTime,

    var reason: AbsenceReason
) {

    companion object {

        fun map(dtoIn: AbsenceModel) =
            with(dtoIn) {
                Absence(
                    referenceId = referenceId,
                    start = start,
                    end = end,
                    reason = reason
                )
            }

        fun map(dtoIn: AbsenceIn) =
            with(dtoIn) {
                Absence(
                    referenceId = referenceId,
                    start = start,
                    end = end,
                    reason = reason
                )
            }
    }
}