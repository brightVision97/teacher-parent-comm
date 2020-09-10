package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.service.dto.`in`.AbsenceIn
import com.rachev.teacherparentcomm.service.dto.out.Absence
import reactor.core.publisher.Flux

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
interface AbsenceService {

    fun findAll(): Flux<Absence>

    fun findByReferenceId(referenceId: String): AbsenceModel

    fun createOrUpdate(dto: AbsenceIn)
}