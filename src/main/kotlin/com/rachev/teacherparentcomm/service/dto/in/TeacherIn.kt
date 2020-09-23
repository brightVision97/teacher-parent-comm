package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.AbsenceForm

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class TeacherIn(
    var participant: ParticipantIn?,
    var subject: String?,
    var students: MutableSet<StudentIn>? = mutableSetOf(),
    var writtenAbsences: MutableSet<AbsenceIn>? = mutableSetOf()
) {

    companion object {

        fun map(model: AbsenceForm.TeacherForm) =
            with(model) {
                TeacherIn(
                    participant = ParticipantIn(
                        referenceId = null,
                        name = participant.name,
                        type = participant.type,
                        gender = participant.gender,
                        isInitiator = participant.isInitiator
                    ),
                    subject = subject,
                    students = students.map { StudentIn.map(it) }.toMutableSet(),
                    writtenAbsences = writtenAbsences.map { AbsenceIn.map(it) }.toMutableSet()
                )
            }
    }
}