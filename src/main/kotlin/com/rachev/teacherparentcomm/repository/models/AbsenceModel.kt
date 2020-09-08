package com.rachev.teacherparentcomm.repository.models

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 07/09/2020
 */
@Entity
@Table(name = "absence")
class AbsenceModel(

    val start: LocalDateTime,

    val end: LocalDateTime,

    @Enumerated(value = EnumType.STRING)
    val reason: AbsenceReason

) : BaseModel()