package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.AbsenceRepository
import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.ParentModel
import com.rachev.teacherparentcomm.repository.models.StudentModel
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

    override fun save(dto: AbsenceIn): Mono<Absence> {
        return Mono.just(
            AbsenceModel(
                start = dto.start,
                end = dto.end,
                student = StudentModel(participant = dto.student.participant).apply {
                    parents.addAll(
                        dto.student.parents.map {
                            ParentModel(
                                participant = participant,
                                studentKids = it.studentKids.map { s ->
                                    StudentModel(
                                        participant = s.participant,
                                        parents = s.parents.map { p ->
                                            ParentModel(
                                                participant = p.participant,
                                                studentKids = p.studentKids.map {
                                                    StudentModel(
                                                        participant = participant,
                                                        parents = parents,
                                                        absences = absences
                                                    )
                                                }.toMutableSet())
                                        }.toMutableSet()
                                    )
                                }.toMutableSet()
                            )
                        }.toMutableSet()
                    )
                    absences.addAll(
                        dto.student.absences.map {
                            AbsenceModel(
                                start = it.start,
                                end = it.end,
                                reason = it.reason,
                                student = StudentModel(
                                    participant = participant,
                                    parents = parents,
                                    absences = absences
                                )
                            )
                        }
                    )
                },
                issuingTeacher = TeacherModel(
                    dto.issuingTeacher.participant,
                    dto.issuingTeacher.subject
                ),
                reason = dto.reason
            )
        ).map { Absence.map(it) }
    }
}