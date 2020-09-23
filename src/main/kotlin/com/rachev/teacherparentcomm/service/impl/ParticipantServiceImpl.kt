package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.ParticipantRepository
import com.rachev.teacherparentcomm.repository.models.ParticipantModel
import com.rachev.teacherparentcomm.service.ParticipantService
import com.rachev.teacherparentcomm.service.dto.`in`.ParticipantIn
import com.rachev.teacherparentcomm.service.dto.out.Participant
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
@Service
@Primary
class ParticipantServiceImpl(
    private val participantRepository: ParticipantRepository
) : ParticipantService {

    override fun find(referenceId: String?): Participant = Participant.map(findByReferenceId(referenceId ?: ""))

    override fun findAll(): Flux<Participant> {
        return Flux.fromIterable(
            participantRepository.findAll().map {
                Participant.map(it)
            }
        )
    }

    override fun findByReferenceId(referenceId: String?): ParticipantModel? =
        participantRepository.findByReferenceId(referenceId ?: "")

    @Transactional
    override fun createOrUpdate(dto: ParticipantIn): Mono<Participant> {

        return Mono.fromCallable {
            val dbEntry = findByReferenceId(dto.referenceId)?.let {
                it.apply {
                    name = if (name == dto.name) name else dto.name
                    type = if (type == dto.type) type else dto.type
                    gender = if (gender == dto.gender) gender else dto.gender
                    isInitiator = dto.isInitiator

                    return@let participantRepository.save(this)
                }
            } ?: with(dto) {
                val toPersist = ParticipantModel(
                    name = name,
                    type = type,
                    gender = gender,
                    isInitiator = isInitiator
                )

                participantRepository.save(toPersist)
            }

            return@fromCallable Participant.map(dbEntry)
        }
    }
}