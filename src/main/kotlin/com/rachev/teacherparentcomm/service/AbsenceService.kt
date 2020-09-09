package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.service.dto.`in`.AbsenceIn
import com.rachev.teacherparentcomm.service.dto.out.Absence
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
interface AbsenceService {

    fun findAll(): Flux<Absence>

    fun save(dto: AbsenceIn): Mono<Absence>
}