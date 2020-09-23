package com.rachev.teacherparentcomm.service.listener.dto

import javax.persistence.Embeddable

/**
 * @author Ivan Rachev
 * @since 17/09/2020
 */
@Embeddable
data class MeetingRequestEvent(
    var initiatorReferenceId: String?,
    var addressantReferenceId: String?,
    var meetingReferenceId: String?
)