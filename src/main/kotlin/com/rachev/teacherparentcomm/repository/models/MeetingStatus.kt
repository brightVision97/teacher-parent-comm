package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Schema(description = "The status of a booked meeting")
enum class MeetingStatus {

    REJECTED,

    POSTPONED,

    DELAYED,

    APPROVED,

    PENDING,

    ;
}