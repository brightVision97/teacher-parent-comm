package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.AbsenceForm.StudentForm
import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.StudentModel
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class StudentIn(
    val participant: MeetingParticipant,
    val parents: MutableSet<ParentIn> = mutableSetOf(),
    val absences: MutableSet<AbsenceIn> = mutableSetOf()
) {

    companion object {

        fun map(model: StudentModel): StudentIn =
            with(model) {
                StudentIn(
                    participant = participant,
                    parents = parents.map { ParentIn.map(it) }.toMutableSet()
                )
            }

        fun map(form: StudentForm): StudentIn =
            with(form) {
                StudentIn(
                    participant = participant,
                    parents = parents.map {
                        ParentIn.map(it)
                    }.toMutableSet(),
                    absences = absences.map {
                        AbsenceIn(
                            start = it.start,
                            end = it.end,
                            issuingTeacher = TeacherIn.map(it.issuingTeacher),
                            reason = it.reason,
                            student = StudentIn(
                                participant = participant,
                                parents = parents.map { p ->
                                    ParentIn.map(p)
                                }.toMutableSet(),
                                absences = absences.map { a ->
                                    AbsenceIn(
                                        start = a.start,
                                        end = a.end,
                                        issuingTeacher = TeacherIn.map(a.issuingTeacher),
                                        reason = a.reason,
                                        student = with(form) {
                                            StudentIn(
                                                participant = participant,
                                                parents = parents.map { pf ->
                                                    ParentIn.map(pf)
                                                }.toMutableSet())
                                        })
                                }.toMutableSet()))
                    }.toMutableSet())
            }
    }
}
