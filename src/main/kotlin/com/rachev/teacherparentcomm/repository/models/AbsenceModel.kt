package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 07/09/2020
 */
@Entity
@Table(name = "absence")
@Schema(description = "A model class representing an absence of a student")
class AbsenceModel(

    var start: LocalDateTime,

    var end: LocalDateTime,

    @Enumerated(value = EnumType.STRING)
    var reason: AbsenceReason,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    var issuingTeacher: TeacherModel? = null

) : AbstractJpaPersistable<Long>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AbsenceModel) return false
        if (!super.equals(other)) return false

        if (start != other.start) return false
        if (end != other.end) return false
        if (issuingTeacher != other.issuingTeacher) return false
        if (reason != other.reason) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + end.hashCode()
        result = 31 * result + (issuingTeacher?.hashCode() ?: 0)
        result = 31 * result + reason.hashCode()
        return result
    }

    override fun toString(): String {
        return "AbsenceModel(start=$start, " +
            "end=$end, " +
            "issuingTeacher=$issuingTeacher, " +
            "reason=$reason, " +
            ")"
    }
}