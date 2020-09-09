package com.rachev.teacherparentcomm.repository.models

import javax.persistence.CascadeType
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Entity
@Table(name = "parent")
class ParentModel(

    @Embedded
    val participant: MeetingParticipant,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var studentKids: MutableSet<StudentModel> = mutableSetOf()

) : AbstractJpaPersistable<Long>() {

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other !is ParentModel) return false
        if (!super.equals(other)) return false

        if (participant != other.participant) return false
        if (studentKids != other.studentKids) return false

        return true
    }

    override fun hashCode(): Int {

        var result = super.hashCode()
        result = 31 * result + participant.hashCode()
        result = 31 * result + studentKids.hashCode()
        return result
    }

    override fun toString(): String {

        return "ParentModel(participant=$participant, " +
            "studentKids=$studentKids)"
    }
}