package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.TeacherModel
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class Teacher(
    var participant: Participant,
    var subject: String?,
    var students: MutableSet<Student> = mutableSetOf(),
    var writtenAbsences: MutableSet<Absence> = mutableSetOf()
) {

    companion object {

        fun map(model: TeacherModel) =
            with(model) {
                Teacher(
                    participant = Participant.map(participant),
                    subject = subject,
                    students = students.map { Student.map(it) }.toMutableSet(),
                    writtenAbsences = writtenAbsences.map { Absence.map(it) }.toMutableSet()
                )
            }
    }
}