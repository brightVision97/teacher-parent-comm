package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.MeetingForm
import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import com.rachev.teacherparentcomm.repository.models.MeetingStatus
import com.rachev.teacherparentcomm.repository.models.ParticipantGender
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Validated
data class MeetingIn(

    @NotEmpty
    @Size(min = 5)
    val title: String,
    val start: LocalDateTime?,
    val end: LocalDateTime?,

    @NotEmpty
    val status: MeetingStatus,

    @NotEmpty
    val participants: MutableSet<ParticipantIn>
) {
    companion object {

        fun map(form: MeetingForm) =
            with(form) {
                MeetingIn(
                    title = title,
                    start = start,
                    end = end,
                    status = status,
                    participants = participants.toMutableSet()
                )
            }
    }

    @Validated
    data class ParticipantIn(

        @NotEmpty
        val name: String,

        @NotNull
        val type: MeetingParticipantType,

        @NotNull
        val gender: ParticipantGender
    )
}