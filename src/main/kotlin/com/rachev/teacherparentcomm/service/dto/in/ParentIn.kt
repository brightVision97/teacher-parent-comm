package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.AbsenceForm.ParentForm
import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.ParentModel
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class ParentIn(
    val participant: MeetingParticipant,
    var studentKids: MutableSet<StudentIn> = mutableSetOf()
) {
    companion object {

        fun map(model: ParentModel): ParentIn =
            with(model) {
                ParentIn(
                    participant = participant,
                    studentKids = studentKids.map {
                        StudentIn.map(it)
                    }.toMutableSet()
                )
            }

        fun map(form: ParentForm): ParentIn =
            with(form) {
                ParentIn(
                    participant = participant,
                    studentKids = studentKids.map {
                        StudentIn.map(it)
                    }.toMutableSet()
                )
            }
    }
}