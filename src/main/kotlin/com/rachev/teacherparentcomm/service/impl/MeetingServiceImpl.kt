package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.MeetingRepository
import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.service.MeetingService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import com.rachev.teacherparentcomm.util.sort
import org.slf4j.LoggerFactory.getLogger
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Service
@Primary
class MeetingServiceImpl(
    private val meetingRepository: MeetingRepository
) : MeetingService {

    companion object {

        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        internal val logger = getLogger(javaClass.enclosingClass)
    }

    override fun findAll() =
        Flux.fromIterable(
            meetingRepository.findAll(sort).map {
                Meeting.map(it)
            }
        )

    override fun findByReferenceId(referenceId: String) =
        meetingRepository.findByReferenceId(referenceId)

    @Transactional
    override fun createOrUpdate(dto: MeetingIn) {

        val dbEntry = if (!dto.referenceId.isNullOrEmpty()) {
            logger.debug("Trying to update existing meeting, referenceId='${dto.referenceId}'")
            meetingRepository.findByReferenceId(dto.referenceId).apply {

                title = if (title === dto.title) title else dto.title
                start = if (start === dto.start) start else dto.start!!
                end = if (end === dto.end) end else dto.end!!
                status = if (status === dto.status) status else dto.status
                participants = (participants union (dto.participants.map {
                    MeetingParticipant(
                        name = it.name,
                        type = it.type,
                        gender = it.gender,
                        isInitiator = it.isInitiator ?: false
                    )
                })).toMutableSet()
            }
        } else null

        meetingRepository.save(dbEntry ?: buildMeeting(dto))
    }

    private final fun buildMeeting(dto: MeetingIn): MeetingModel {

        return with(dto) {
            MeetingModel(
                start = start!!,
                end = end!!,
                title = title,
                status = status
            ).apply {
                assert(participants.isEmpty())
                participants.addAll(
                    dto.participants.map {
                        MeetingParticipant(
                            name = it.name,
                            type = it.type,
                            gender = it.gender,
                            isInitiator = it.isInitiator ?: false
                        )
                    }
                )
            }
        }
    }
}