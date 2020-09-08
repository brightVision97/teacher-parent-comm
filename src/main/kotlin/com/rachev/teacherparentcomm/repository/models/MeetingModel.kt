package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Entity
@Table(name = "meeting")
@Schema(description = "A model representing a general meeting, regardless of participants")
class MeetingModel(

    var title: String,

    var start: LocalDateTime,

    var end: LocalDateTime,

    @Enumerated(value = EnumType.STRING)
    var status: MeetingStatus

) : BaseReferenceModel() {

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var participants = mutableSetOf<BaseMeetingParticipantModel>()

    fun getDuration() = Duration.between(start, end).toMinutes()

    fun hasStarted() = LocalDateTime.now().isAfter(start)

    fun hasEnded() = LocalDateTime.now().isAfter(end)

    fun isOngoing() = LocalDateTime.now().let { it.isAfter(start) && it.isBefore(end) }
}