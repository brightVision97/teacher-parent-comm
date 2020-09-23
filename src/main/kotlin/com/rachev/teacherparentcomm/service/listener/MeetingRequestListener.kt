package com.rachev.teacherparentcomm.service.listener

import com.rachev.teacherparentcomm.service.MeetingRequestService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingRequestIdsIn
import com.rachev.teacherparentcomm.service.listener.dto.MeetingRequestEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * @author Ivan Rachev
 * @since 17/09/2020
 */
@Component
class MeetingRequestListener(
    private val meetingRequestService: MeetingRequestService
) {

    @EventListener
    fun handleMeetingRequest(event: MeetingRequestEvent) {

        with(event) {
            meetingRequestService.request(
                MeetingRequestIdsIn(
                    initiatorReferenceId = initiatorReferenceId,
                    addressantReferenceId = addressantReferenceId,
                    meetingReferenceId = meetingReferenceId
                )
            )
        }
    }
}