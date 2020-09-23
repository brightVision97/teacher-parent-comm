package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.StudentModel

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class Student(
    val participant: Participant,
    val parents: MutableSet<Parent> = mutableSetOf(),
    val absences: MutableSet<Absence> = mutableSetOf()
) {

    companion object {

        fun map(model: StudentModel) =
            with(model) {
                Student(
                    participant = Participant.map(participant),
                    parents = parents.map {
                        Parent(Participant.map(it.participant))
                    }.toMutableSet()
                ).apply {
                    this.parents.addAll(parents)
                }
            }
    }
}