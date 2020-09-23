package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import com.rachev.teacherparentcomm.repository.models.ParticipantGender

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
data class ParticipantIn(
    val referenceId: String? = null,
    val name: String?,
    var type: MeetingParticipantType?,
    val gender: ParticipantGender?,
    var isInitiator: Boolean?
)