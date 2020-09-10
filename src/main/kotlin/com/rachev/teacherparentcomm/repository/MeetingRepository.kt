package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.MeetingModel

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
interface MeetingRepository : AbstractJpaRepository<MeetingModel> {

    fun findByReferenceId(referenceId: String): MeetingModel
}