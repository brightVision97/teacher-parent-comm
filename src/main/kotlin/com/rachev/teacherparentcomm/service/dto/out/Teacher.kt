package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.StudentModel
import com.rachev.teacherparentcomm.repository.models.TeacherModel
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class Teacher(
    var participant: MeetingParticipant,
    var subject: String?,
    var students: MutableSet<StudentModel>,
    var writtenAbsences: Set<AbsenceModel>
) {

    companion object {

        fun map(model: TeacherModel) =
            with(model) {
                Teacher(
                    participant = participant,
                    subject = subject,
                    students = students,
                    writtenAbsences = writtenAbsences
                )
            }
    }
}