package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.MeetingRequestModel

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
interface MeetingRequestRepository : AbstractJpaRepository<MeetingRequestModel> {

    fun findByReferenceId(referenceId: String): MeetingRequestModel?
}