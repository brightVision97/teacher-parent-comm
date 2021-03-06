package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import com.rachev.teacherparentcomm.service.listener.dto.MeetingRequestEvent
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
interface MeetingService {

    fun findByReferenceId(referenceId: String?): MeetingModel?

    fun findAll(): Flux<Meeting>

    fun createOrUpdate(dto: MeetingIn): Mono<Meeting>
}