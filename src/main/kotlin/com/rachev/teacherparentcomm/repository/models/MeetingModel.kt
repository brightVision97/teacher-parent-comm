package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema
import java.io.ByteArrayInputStream
import java.time.Duration
import java.time.LocalDateTime
import javax.persistence.CollectionTable
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
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

) : AbstractJpaPersistable<Long>() {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "meeting_meeting_participant",
        joinColumns = [
            JoinColumn(name = "meeting_id")
        ]
    )
    var participants: MutableSet<MeetingParticipant> = mutableSetOf()

    @Transient
    val additionalData: Collection<ByteArrayInputStream> = emptySet()

    fun getDuration() = Duration.between(start, end).toMinutes()

    fun hasStarted() = LocalDateTime.now().isAfter(start)

    fun hasEnded() = LocalDateTime.now().isAfter(end)

    fun isOngoing() = LocalDateTime.now().let { it.isAfter(start) && it.isBefore(end) }

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other !is MeetingModel) return false
        if (!super.equals(other)) return false

        if (title != other.title) return false
        if (start != other.start) return false
        if (end != other.end) return false
        if (status != other.status) return false
        if (participants != other.participants) return false
        if (additionalData != other.additionalData) return false

        return true
    }

    override fun hashCode(): Int {

        var result = super.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + end.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + participants.hashCode()
        result = 31 * result + additionalData.hashCode()
        return result
    }

    override fun toString(): String {

        return "MeetingModel(title='$title', " +
            "start=$start, " +
            "end=$end, " +
            "status=$status, " +
            "participants=$participants," +
            " additionalData=$additionalData" +
            ")"
    }
}