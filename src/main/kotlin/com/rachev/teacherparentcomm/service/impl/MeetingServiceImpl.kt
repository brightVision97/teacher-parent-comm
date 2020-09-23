package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.MeetingRepository
import com.rachev.teacherparentcomm.repository.ParticipantRepository
import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.repository.models.ParticipantModel
import com.rachev.teacherparentcomm.service.MeetingService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.`in`.ParticipantIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import com.rachev.teacherparentcomm.service.listener.dto.MeetingRequestEvent
import com.rachev.teacherparentcomm.util.sort
import mu.KotlinLogging
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger { }

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Service
@Primary
class MeetingServiceImpl(
    private val meetingRepository: MeetingRepository,
    private val participantRepository: ParticipantRepository,
    private val eventPublisher: ApplicationEventPublisher
) : MeetingService {

    override fun findByReferenceId(referenceId: String?): MeetingModel? {
        return meetingRepository.findByReferenceId(referenceId ?: "")
    }

    override fun findAll(): Flux<Meeting> =
        Flux.fromIterable(
            meetingRepository.findAll(sort).map { Meeting.map(it) }
        )

    @Transactional
    override fun createOrUpdate(dto: MeetingIn): Mono<Meeting> {

        val dbEntry = if (!dto.referenceId.isNullOrEmpty()) {
            logger.debug("Trying to update existing meeting, referenceId='${dto.referenceId}'")
            meetingRepository.findByReferenceId(dto.referenceId)
        } else null

        validateInitiator(dto.participants)

        dbEntry?.apply {
            title = if (title == dto.title) title else dto.title
            start = if (start == dto.start) start else dto.start
            end = if (end == dto.end) end else dto.end
            participants = (participants union dto.participants.map {
                participantRepository.save(
                    ParticipantModel(
                        name = it.name,
                        type = it.type,
                        gender = it.gender,
                        isInitiator = it.isInitiator
                    )
                )
            }).toMutableSet()
        }

        val resultModel = dbEntry ?: buildMeeting(Meeting.map(dto))

        eventPublisher.publishEvent(
            MeetingRequestEvent(
                initiatorReferenceId = resultModel.participants.find { it.isInitiator ?: false }?.referenceId,
                addressantReferenceId = resultModel.participants.find { it.isInitiator?.not() ?: false }?.referenceId,
                meetingReferenceId = dbEntry?.referenceId
            )
        )

        return Mono.just(Meeting.map(meetingRepository.save(resultModel)))
    }

    private fun validateInitiator(participants: Set<ParticipantIn>) {
        if (participants.count { it.isInitiator == true } > 1) throw IllegalStateException("Only 1 initiator is allowed")
    }

    private fun buildMeeting(dto: Meeting) =
        with(dto) {
            MeetingModel(
                start = start,
                end = end,
                title = title,
            ).apply {
                participants = (participants union dto.participants.map {
                    participantRepository.save(
                        ParticipantModel(
                            name = it.name,
                            type = it.type,
                            gender = it.gender,
                            isInitiator = it.isInitiator
                        )

                    )
                }).toMutableSet()
            }
        }
}