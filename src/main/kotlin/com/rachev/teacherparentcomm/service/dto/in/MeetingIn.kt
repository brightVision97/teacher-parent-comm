package com.rachev.teacherparentcomm.service.dto.`in`

import com.fasterxml.jackson.annotation.JsonFormat
import com.rachev.teacherparentcomm.controller.form.MeetingForm
import com.rachev.teacherparentcomm.repository.models.MeetingRequestStatus
import com.rachev.teacherparentcomm.service.dto.out.Participant
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
data class MeetingIn(

    val referenceId: String? = null,

    var title: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var start: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var end: LocalDateTime,

    var participants: MutableSet<ParticipantIn>
) {

    companion object {

        fun map(obj: MeetingForm) =

            with(obj) {
                MeetingIn(
                    referenceId = referenceId,
                    title = title,
                    start = start,
                    end = end,
                    participants = participants.map {
                        ParticipantIn(
                            name = it.name,
                            type = it.type,
                            gender = it.gender,
                            isInitiator = it.isInitiator
                        )
                    }.toMutableSet()
                )
            }
    }
}
