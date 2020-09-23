package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.ParticipantModel

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
interface ParticipantRepository : AbstractJpaRepository<ParticipantModel> {

    fun findByReferenceId(referenceId: String): ParticipantModel?
}