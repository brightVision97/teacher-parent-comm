package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.service.dto.`in`.MeetingRequestIdsIn

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
@FunctionalInterface
interface MeetingRequestService {

    fun request(request: MeetingRequestIdsIn)
}