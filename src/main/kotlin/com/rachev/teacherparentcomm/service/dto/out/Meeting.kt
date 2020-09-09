package com.rachev.teacherparentcomm.service.dto.out

import com.fasterxml.jackson.annotation.JsonFormat
import com.rachev.teacherparentcomm.controller.form.MeetingForm
import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import java.time.LocalDateTime

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
data class Meeting(

    var title: String? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var start: LocalDateTime? = null,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var end: LocalDateTime? = null,

    var participants: MutableSet<Participant>
) {

    companion object {

        fun map(obj: Any) =

            when (obj) {
                is MeetingModel -> Meeting(
                    title = obj.title,
                    start = obj.start,
                    participants = obj.participants.map {
                        Participant(
                            it.name,
                            it.type
                        )
                    }.toMutableSet()
                )
                is MeetingForm -> Meeting(
                    title = obj.title,
                    start = obj.start,
                    end = obj.end,
                    participants = obj.participants.map {
                        Participant(
                            it.name,
                            it.type
                        )
                    }.toMutableSet()
                )
                else -> Any() as Meeting
            }
    }

    data class Participant(
        val name: String,
        val type: MeetingParticipantType
    )
}
