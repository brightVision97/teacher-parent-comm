package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.AbsenceRepository
import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.TeacherModel
import com.rachev.teacherparentcomm.service.AbsenceService
import com.rachev.teacherparentcomm.service.dto.`in`.AbsenceIn
import com.rachev.teacherparentcomm.service.dto.out.Absence
import com.rachev.teacherparentcomm.util.sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Service
class AbsenceServiceImpl(
    private val absenceRepository: AbsenceRepository
) : AbsenceService {

    override fun findAll() =
        Flux.fromIterable(
            absenceRepository.findAll(sort).map {
                Absence.map(it)
            }
        )

    override fun save(dto: AbsenceIn) =
        with(dto) {
            Mono.just(
                AbsenceModel(
                    start = start,
                    end = end,
                    issuingTeacher = TeacherModel(
                        participant = issuingTeacher.participant,
                        subject = issuingTeacher.subject
                    ),
                    reason = dto.reason
                )
            ).map {
                Absence.map(it)
            }
        }
}
