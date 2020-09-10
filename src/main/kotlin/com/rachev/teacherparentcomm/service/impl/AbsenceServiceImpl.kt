package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.AbsenceRepository
import com.rachev.teacherparentcomm.repository.models.AbsenceModel
import com.rachev.teacherparentcomm.repository.models.TeacherModel
import com.rachev.teacherparentcomm.service.AbsenceService
import com.rachev.teacherparentcomm.service.dto.`in`.AbsenceIn
import com.rachev.teacherparentcomm.service.dto.out.Absence
import com.rachev.teacherparentcomm.util.sort
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Service
@Primary
class AbsenceServiceImpl(
    private val absenceRepository: AbsenceRepository
) : AbsenceService {

    companion object {

        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        internal val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    override fun findAll() =
        Flux.fromIterable(
            absenceRepository.findAll(sort).map {
                Absence.map(it)
            }
        )

    override fun findByReferenceId(referenceId: String) =
        absenceRepository.findByReferenceId(referenceId)

    @Transactional
    override fun createOrUpdate(dto: AbsenceIn) {

        val dbEntry = if (!dto.referenceId.isNullOrEmpty()) {
            logger.debug("Trying to update existing absence, referenceId='${dto.referenceId}'")
            absenceRepository.findByReferenceId(dto.referenceId).apply {

                start = if (start === dto.start) start else dto.start
                end = if (start === dto.end) end else dto.end
                reason = if (reason === dto.reason) reason else dto.reason
            }
        } else null

        absenceRepository.save(dbEntry ?: buildAbsenceModel(dto))
    }

    private fun buildAbsenceModel(dto: AbsenceIn) =
        with(dto) {
            AbsenceModel(
                start = start,
                end = end,
                issuingTeacher = TeacherModel(
                    participant = issuingTeacher.participant,
                    subject = issuingTeacher.subject
                ),
                reason = dto.reason
            )

        }
}
