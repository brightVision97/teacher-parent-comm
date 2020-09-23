package com.rachev.teacherparentcomm.repository.models

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 07/09/2020
 */
@Entity
@Table(name = "participant")
class ParticipantModel(

    var name: String? = null,

    @Enumerated(value = EnumType.STRING)
    var type: MeetingParticipantType? = null,

    @Enumerated(value = EnumType.STRING)
    var gender: ParticipantGender? = null,

    var isInitiator: Boolean? = false

) : AbstractJpaPersistable<Long>() {

}