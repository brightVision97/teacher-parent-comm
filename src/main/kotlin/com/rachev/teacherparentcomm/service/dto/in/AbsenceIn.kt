package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.AbsenceForm
import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.AbsenceReason
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class AbsenceIn(
    val start: LocalDateTime,
    val end: LocalDateTime,
    var issuingTeacher: TeacherIn,
    var reason: AbsenceReason
) {

    companion object {

        fun map(model: AbsenceModel): AbsenceIn =
            with(model) {
                return AbsenceIn(
                    start = start,
                    end = end,
                    issuingTeacher = TeacherIn.map(issuingTeacher!!),
                    reason = reason
                )
            }

        fun map(form: AbsenceForm): AbsenceIn =
            with(form) {
                return AbsenceIn(
                    start = start,
                    end = end,
                    issuingTeacher = TeacherIn.map(issuingTeacher),
                    reason = reason
                )
            }
    }
}