package com.rachev.teacherparentcomm.service.dto.out

import com.fasterxml.jackson.annotation.JsonFormat
import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.AbsenceReason
import com.rachev.teacherparentcomm.repository.models.TeacherModel
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class Absence(

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val start: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val end: LocalDateTime,

    var issuingTeacher: TeacherModel,

    var reason: AbsenceReason
) {

    companion object {

        fun map(dtoIn: AbsenceModel) =
            with(dtoIn) {
                Absence(
                    start = start,
                    end = end,
                    issuingTeacher = TeacherModel(
                        participant = issuingTeacher?.participant!!,
                        subject = issuingTeacher?.subject
                    ),
                    reason = reason
                )
            }
    }
}