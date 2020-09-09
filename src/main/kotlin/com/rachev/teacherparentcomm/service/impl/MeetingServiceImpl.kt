package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.MeetingRepository
import com.rachev.teacherparentcomm.service.MeetingService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import com.rachev.teacherparentcomm.util.sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Service
class MeetingServiceImpl(
    private val meetingRepository: MeetingRepository
) : MeetingService {

    override fun findAll() =
        Flux.fromIterable(
            meetingRepository.findAll(sort).map {
                Meeting.map(it)
            }
        )

    override fun findByReferenceId(referenceId: String): Mono<Meeting> =
        Mono.just(
            Meeting.map(
                meetingRepository.findByReferenceId(referenceId)
            )
        )

    override fun save(dto: MeetingIn): Mono<Meeting> {
        TODO("Not yet implemented")
    }
}