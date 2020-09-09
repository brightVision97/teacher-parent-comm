package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.controller.form.MeetingForm.ParticipantForm
import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import com.rachev.teacherparentcomm.repository.models.ParticipantGender
import io.swagger.v3.oas.annotations.Hidden

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Hidden
data class ParticipantIn(
    var name: String,
    var type: MeetingParticipantType,
    var gender: ParticipantGender,
    var isInitiator: Boolean
) {

    companion object {

        fun map(embeddable: MeetingParticipant): ParticipantIn =
            with(embeddable) {
                ParticipantIn(
                    name = name,
                    type = type,
                    gender = gender,
                    isInitiator = isInitiator
                )
            }

        fun map(form: ParticipantForm): ParticipantIn =
            with(form) {
                ParticipantIn(
                    name = name,
                    type = type,
                    gender = gender,
                    isInitiator = isInitiator
                )
            }
    }
}