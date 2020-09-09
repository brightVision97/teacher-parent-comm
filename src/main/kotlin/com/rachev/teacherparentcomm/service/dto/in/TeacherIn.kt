package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.AbsenceForm.TeacherForm
import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.TeacherModel
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class TeacherIn(
    var participant: MeetingParticipant,
    var subject: String?,
    var students: MutableSet<StudentIn> = mutableSetOf(),
    var writtenAbsences: MutableSet<AbsenceIn> = mutableSetOf()
) {

    companion object {

        fun map(form: TeacherForm) =
            with(form) {
                TeacherIn(
                    participant = participant,
                    subject = subject,
                    students = students.map {
                        StudentIn.map(it)
                    }.toMutableSet(),
                    writtenAbsences = writtenAbsences.map {
                        AbsenceIn.map(it)
                    }.toMutableSet()
                )
            }

        fun map(model: TeacherModel) =
            with(model) {
                TeacherIn(
                    participant = participant,
                    subject = subject,
                    students = students.map {
                        StudentIn.map(it)
                    }.toMutableSet(),
                    writtenAbsences = writtenAbsences.map {
                        AbsenceIn.map(it)
                    }.toMutableSet()
                )
            }
    }
}