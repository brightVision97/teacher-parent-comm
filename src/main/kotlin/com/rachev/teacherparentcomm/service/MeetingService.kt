package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
interface MeetingService {

    fun findAll(): Flux<Meeting>

    fun findByReferenceId(referenceId: String): Mono<Meeting>

    fun createOrUpdate(dto: MeetingIn)
}