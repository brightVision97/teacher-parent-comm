package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Schema(description = "The requestStatus of a booked meeting")
enum class MeetingRequestStatus(furtherInfo: String? = null) {

    REJECTED("The meeting request was rejected by one of the parties."),

    REQUESTED("The meeting has been requested."),

    APPROVED("The meeting is set to take place in the near future."),

    ;
}