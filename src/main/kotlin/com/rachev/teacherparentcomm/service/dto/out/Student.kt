package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.StudentModel
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class Student(
    val participant: MeetingParticipant,
    val parents: MutableSet<Parent> = hashSetOf(),
    val absences: MutableSet<Absence> = mutableSetOf()
) {

    companion object {

        fun map(model: StudentModel) =
            with(model) {
                Student(
                    participant = participant
                ).apply {
                    this.parents.addAll(parents)
                }
            }
    }
}