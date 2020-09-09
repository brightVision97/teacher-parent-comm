package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.Hidden
import javax.persistence.Embeddable

/**
 * @author Ivan Rachev
 * @since 07/09/2020
 */
@Embeddable
@Hidden
class MeetingParticipant(
    var name: String,
    var type: MeetingParticipantType,
    var gender: ParticipantGender,
    var isInitiator: Boolean
)