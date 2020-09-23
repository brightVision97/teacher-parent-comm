package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.Index
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 17/09/2020
 */
@Entity
@Table(
    name = "conversation",
    indexes = [
        Index(
            name = "conversation_referenceId__idx",
            columnList = "referenceId",
            unique = true
        )
    ]
)
@Schema(description = "A conversation that's automatically initiated and attached to on meeting request")
class ConversationModel(

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "conversation_participant",
        joinColumns = [JoinColumn(name = "conversation_id")]
    )
    var participants: Set<ParticipantModel> = hashSetOf(),

    @ManyToMany
    var segments: MutableList<ConversationSegmentModel> = mutableListOf(),

    @OneToOne
    var relatedRequest: MeetingRequestModel? = null,

    @Enumerated(value = EnumType.STRING)
    var status: ConversationStatus? = null

) : AbstractJpaPersistable<Long>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConversationModel) return false
        if (!super.equals(other)) return false

        if (participants != other.participants) return false
        if (segments != other.segments) return false
        if (relatedRequest != other.relatedRequest) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + participants.hashCode()
        result = 31 * result + segments.hashCode()
        result = 31 * result + (relatedRequest?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ConversationModel(" +
            "participants=$participants, " +
            "segments=$segments, " +
            "relatedRequest=$relatedRequest," +
            " status=$status" +
            ")"
    }
}