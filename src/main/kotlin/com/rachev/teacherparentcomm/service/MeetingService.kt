package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import reactor.core.publisher.Flux

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
interface MeetingService {

    fun findAll(): Flux<Meeting>

    fun findByReferenceId(referenceId: String): MeetingModel

    fun createOrUpdate(dto: MeetingIn)
}