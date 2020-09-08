package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.MeetingRepository
import com.rachev.teacherparentcomm.repository.models.MeetingModel
import com.rachev.teacherparentcomm.repository.models.MeetingParticipantType
import com.rachev.teacherparentcomm.repository.models.ParentModel
import com.rachev.teacherparentcomm.repository.models.StudentModel
import com.rachev.teacherparentcomm.repository.models.TeacherModel
import com.rachev.teacherparentcomm.service.MeetingService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.service.dto.out.Meeting
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Service
class MeetingServiceImpl(
    private val meetingRepository: MeetingRepository
) : MeetingService {

    private companion object {
        private val sort = Sort.by(Sort.Direction.DESC, "start")
    }

    override fun findAll() =
        Flux.fromIterable(
            meetingRepository.findAll(sort).map {
                Meeting.map(it)
            }
        )

    override fun save(dto: MeetingIn) =
        Mono.just(
            meetingRepository.save(
                MeetingModel(
                    title = dto.title,
                    start = dto.start!!,
                    end = dto.end!!,
                    status = dto.status
                ).apply {
                    participants = dto.participants.map {
                        when (it.type) {
                            MeetingParticipantType.STUDENT -> StudentModel().apply {
                                name = it.name
                                type = it.type
                                gender = it.gender
                            }
                            MeetingParticipantType.PARENT -> ParentModel().apply {
                                name = it.name
                                type = it.type
                                gender = it.gender
                            }
                            MeetingParticipantType.TEACHER -> TeacherModel(null).apply {
                                name = it.name
                                type = it.type
                                gender = it.gender
                            }
                            else -> throw IllegalArgumentException()
                        }
                    }.toMutableSet()
                }
            )
        ).map {
            Meeting.map(it)
        }
}

