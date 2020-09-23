package com.rachev.teacherparentcomm.repository.models

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Entity
@Table(name = "teacher")
class TeacherModel(

    @OneToOne
    val participant: ParticipantModel,

    var subject: String?,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        name = "student_teacher",
        joinColumns = [JoinColumn(name = "teacher_id")],
        inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    var students: MutableSet<StudentModel> = mutableSetOf(),

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var writtenAbsences: Set<AbsenceModel> = mutableSetOf()

) : AbstractJpaPersistable<Long>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is TeacherModel) return false
        if (!super.equals(other)) return false

        if (participant != other.participant) return false
        if (subject != other.subject) return false
        if (students != other.students) return false
        if (writtenAbsences != other.writtenAbsences) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + participant.hashCode()
        result = 31 * result + (subject?.hashCode() ?: 0)
        result = 31 * result + students.hashCode()
        result = 31 * result + writtenAbsences.hashCode()
        return result
    }

    override fun toString(): String {
        return "TeacherModel(participant=$participant, subject=$subject, students=$students, writtenAbsences=$writtenAbsences)"
    }
}