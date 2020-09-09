package com.rachev.teacherparentcomm.repository.models

import javax.persistence.CascadeType
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Entity
@Table(name = "student")
class StudentModel(

    @Embedded
    val participant: MeetingParticipant,

    ) : AbstractJpaPersistable<Long>() {

    companion object {
        const val DEFAULT_PARENTS_COUNT = 2
        const val ONE_PARENT = 1
    }

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var parents: MutableSet<ParentModel> = hashSetOf()

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var absences: MutableSet<AbsenceModel> = mutableSetOf()

    @Transient
    private val getParentsPair =
        parents.takeUnless { it.size != DEFAULT_PARENTS_COUNT }?.let { _parents ->
            _parents.takeIf {
                it.count { _p -> _p.participant.gender == ParticipantGender.MALE } == ONE_PARENT &&
                    it.count { _p -> _p.participant.gender == ParticipantGender.FEMALE } == ONE_PARENT
            }
        }?.let { _parentss ->
            return@let Pair(
                first = _parentss.firstOrNull(),
                second = _parentss.lastOrNull())
        } ?: throw IllegalStateException("Failed to obtain the pair of parents")

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other !is StudentModel) return false
        if (!super.equals(other)) return false

        if (participant != other.participant) return false
        if (parents != other.parents) return false
        if (absences != other.absences) return false
        if (getParentsPair != other.getParentsPair) return false

        return true
    }

    override fun hashCode(): Int {

        var result = super.hashCode()
        result = 31 * result + participant.hashCode()
        result = 31 * result + parents.hashCode()
        result = 31 * result + absences.hashCode()
        result = 31 * result + getParentsPair.hashCode()
        return result
    }

    override fun toString(): String {

        return "StudentModel(participant=$participant, " +
            "parents=$parents, " +
            "absences=$absences," +
            " getParentsPair=$getParentsPair)"
    }
}
