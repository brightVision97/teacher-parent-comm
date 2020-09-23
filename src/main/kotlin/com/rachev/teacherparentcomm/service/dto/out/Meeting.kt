package com.rachev.teacherparentcomm.service.dto.out

import com.fasterxml.jackson.annotation.JsonFormat
import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.repository.models.MeetingRequestStatus
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
data class Meeting(

    val referenceId: String?,

    var title: String?,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var start: LocalDateTime,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var end: LocalDateTime,

    var participants: MutableSet<Participant> = mutableSetOf()
) {

    companion object {

        fun map(obj: Any) =

            when (obj) {
                is MeetingModel -> with(obj) {
                    Meeting(
                        referenceId = referenceId,
                        title = title,
                        start = start,
                        end = end,
                        participants = participants.map {
                            Participant.map(it)
                        }.toMutableSet()
                    )
                }
                is MeetingIn -> with(obj) {
                    Meeting(
                        referenceId = referenceId,
                        title = title,
                        start = start,
                        end = end,
                        participants = participants.map {
                            Participant.map(it)
                        }.toMutableSet()
                    )
                }
                else -> Any() as Meeting
            }
    }

    data class MeetingRequest(
        val initiator: Participant,
        val addresant: Participant,
        val status: MeetingRequestStatus
    )
}
