package com.rachev.teacherparentcomm.repository.models

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Entity
@Table(name = "student")
class StudentModel() : BaseMeetingParticipantModel() {

    companion object {
        const val PARENTS_COUNT = 2
        const val PARENT_HALF = PARENTS_COUNT / 2
    }

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var parents: MutableList<ParentModel> = mutableListOf()

    @Transient
    var getParentsPair =
        parents.takeUnless { it.size != PARENTS_COUNT }?.let { _parents ->
            _parents.takeIf {
                it.count { _p -> _p.gender == ParticipantGender.MALE } == PARENT_HALF &&
                    it.count { _p -> _p.gender == ParticipantGender.FEMALE } == PARENT_HALF
            }
        }?.let { _parentss ->
            return@let Pair(
                first = _parentss[0],
                second = _parentss[1]
            )
        } ?: throw IllegalStateException()
}
