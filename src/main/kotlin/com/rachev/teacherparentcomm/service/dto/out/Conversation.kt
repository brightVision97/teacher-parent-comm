package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.ConversationSegmentModel
import com.rachev.teacherparentcomm.repository.models.ConversationStatus
import com.rachev.teacherparentcomm.repository.models.MeetingRequestModel
import com.rachev.teacherparentcomm.repository.models.ParticipantModel

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
data class Conversation(

    var participants: Set<ParticipantModel> = hashSetOf(),

    var segments: MutableList<ConversationSegmentModel> = mutableListOf(),

    var relatedRequest: MeetingRequestModel? = null,

    var status: ConversationStatus? = null
)