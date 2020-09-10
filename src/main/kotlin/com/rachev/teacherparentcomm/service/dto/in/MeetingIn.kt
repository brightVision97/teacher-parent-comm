package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.MeetingForm
import com.rachev.teacherparentcomm.repository.models.MeetingStatus
import io.swagger.v3.oas.annotations.Hidden
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Hidden
data class MeetingIn(
    val referenceId: String,
    val title: String,
    val start: LocalDateTime?,
    val end: LocalDateTime?,
    val status: MeetingStatus,
    val participants: MutableSet<ParticipantIn>

) {
    companion object {

        fun map(form: MeetingForm) =
            with(form) {
                MeetingIn(
                    referenceId = referenceId,
                    title = title,
                    start = start,
                    end = end,
                    status = status,
                    participants = participants.map {
                        ParticipantIn.map(it)
                    }.toMutableSet()
                )
            }
    }
}