package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.MeetingRepository
import com.rachev.teacherparentcomm.repository.MeetingRequestRepository
import com.rachev.teacherparentcomm.repository.models.MeetingRequestModel
import com.rachev.teacherparentcomm.repository.models.MeetingRequestStatus
import com.rachev.teacherparentcomm.service.MeetingRequestService
import com.rachev.teacherparentcomm.service.ParticipantService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingRequestIdsIn
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
@Service
@Primary
class MeetingRequestServiceImpl(
    private val meetingRequestRepository: MeetingRequestRepository,
    private val meetingRepository: MeetingRepository,
    private val participantService: ParticipantService
) : MeetingRequestService {

    @Transactional
    override fun request(request: MeetingRequestIdsIn) {

        val meeting = meetingRepository.findByReferenceId(request.meetingReferenceId ?: "")
        val initiator = participantService.findByReferenceId(request.initiatorReferenceId)
        val addresant = participantService.findByReferenceId(request.addressantReferenceId)

        val toSave = MeetingRequestModel(
            initiator = initiator,
            addresant = addresant,
            meeting = meeting,
            status = MeetingRequestStatus.REQUESTED
        )

        meeting.apply {
            this.request = toSave
        }

        meetingRepository.save(meeting)

        meetingRequestRepository.save(toSave)
    }
}