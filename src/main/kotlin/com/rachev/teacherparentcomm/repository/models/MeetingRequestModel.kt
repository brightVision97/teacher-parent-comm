package com.rachev.teacherparentcomm.repository.models

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
@Entity
@Table(name = "meeting_request")
class MeetingRequestModel(

    @OneToOne
    val initiator: ParticipantModel? = null,

    @OneToOne
    val addresant: ParticipantModel? = null,

    @OneToOne
    @JoinColumn(name = "meeting_id")
    val meeting: MeetingModel? = null,

    @Enumerated(value = EnumType.STRING)
    val status: MeetingRequestStatus? = null

) : AbstractJpaPersistable<Long>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MeetingRequestModel) return false
        if (!super.equals(other)) return false

        if (initiator != other.initiator) return false
        if (addresant != other.addresant) return false
        if (meeting != other.meeting) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (initiator?.hashCode() ?: 0)
        result = 31 * result + (addresant?.hashCode() ?: 0)
        result = 31 * result + (meeting?.hashCode() ?: 0)
        result = 31 * result + (status?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "MeetingRequestModel(" +
            "initiator=$initiator, " +
            "addresant=$addresant," +
            " meeting=$meeting," +
            " status=$status" +
            ")"
    }
}