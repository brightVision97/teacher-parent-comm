package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.repository.models.ParticipantModel
import com.rachev.teacherparentcomm.service.dto.`in`.ParticipantIn
import com.rachev.teacherparentcomm.service.dto.out.Participant
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
interface ParticipantService {

    fun findByReferenceId(referenceId: String?): ParticipantModel?

    fun find(referenceId: String?): Participant

    fun findAll(): Flux<Participant>

    fun createOrUpdate(dto: ParticipantIn): Mono<Participant>
}