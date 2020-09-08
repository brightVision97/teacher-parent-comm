package com.rachev.teacherparentcomm.repository.models

import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.MappedSuperclass

/**
 * @author Ivan Rachev
 * @since 07/09/2020
 */
@Embeddable
open class BaseMeetingParticipantModel : BaseReferenceModel() {
    lateinit var name: String
    lateinit var type: MeetingParticipantType
    lateinit var gender: ParticipantGender
}