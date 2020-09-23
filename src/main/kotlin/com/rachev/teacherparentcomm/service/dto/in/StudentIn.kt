package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.AbsenceForm

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class StudentIn(
    val participant: ParticipantIn?,
    val parents: MutableSet<ParentIn>? = mutableSetOf(),
    val absences: MutableSet<AbsenceIn>? = mutableSetOf()
) {

    companion object {

        fun map(model: AbsenceForm.StudentForm) =
            with(model) {
                StudentIn(
                    participant = ParticipantIn(
                        referenceId = null,
                        name = participant.name,
                        type = participant.type,
                        gender = participant.gender,
                        isInitiator = participant.isInitiator
                    ),
                    parents = parents.map {
                        ParentIn(
                            ParticipantIn(
                                referenceId = null,
                                name = participant.name,
                                type = participant.type,
                                gender = participant.gender,
                                isInitiator = participant.isInitiator
                            )
                        )
                    }.toMutableSet()
                ).apply {
                    this.parents?.addAll(parents)
                }
            }
    }
}