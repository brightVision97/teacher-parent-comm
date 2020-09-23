package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import com.rachev.teacherparentcomm.repository.models.ParticipantGender
import com.rachev.teacherparentcomm.repository.models.ParticipantModel
import com.rachev.teacherparentcomm.service.dto.`in`.ParticipantIn

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
data class Participant(
    val referenceId: String? = null,
    val name: String?,
    var type: MeetingParticipantType?,
    val gender: ParticipantGender?,
    var isInitiator: Boolean?
) {
    companion object {

        fun map(model: ParticipantModel?) =
            with(model) {
                Participant(
                    referenceId = this?.referenceId,
                    name = this?.name,
                    type = this?.type,
                    gender = this?.gender,
                    isInitiator = this?.isInitiator
                )
            }

        fun map(model: Participant) =
            with(model) {
                Participant(
                    referenceId = referenceId,
                    name = name,
                    type = type,
                    gender = gender,
                    isInitiator = isInitiator
                )
            }

        fun map(model: ParticipantIn) =
            with(model) {
                Participant(
                    referenceId = referenceId,
                    name = name,
                    type = type,
                    gender = gender,
                    isInitiator = isInitiator
                )
            }
    }
}