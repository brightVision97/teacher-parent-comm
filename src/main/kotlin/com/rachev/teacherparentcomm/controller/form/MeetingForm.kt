package com.rachev.teacherparentcomm.controller.form

import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import com.rachev.teacherparentcomm.repository.models.ParticipantGender
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.lang.Nullable
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Validated
data class MeetingForm(

    @Nullable
    var referenceId: String?,

    @NotEmpty
    val title: String,

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val start: LocalDateTime,

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val end: LocalDateTime,

    @NotEmpty
    val participants: Set<ParticipantForm>
) {
    @Validated
    data class ParticipantForm(

        @NotEmpty
        var name: String,

        @NotNull
        var type: MeetingParticipantType,

        @NotNull
        var gender: ParticipantGender,

        @NotNull
        var isInitiator: Boolean
    )
}

