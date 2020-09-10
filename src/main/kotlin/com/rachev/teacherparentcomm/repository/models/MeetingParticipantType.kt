package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema

/**
 * @author Ivan Rachev
 * @since 07/09/2020
 */
@Schema(description = "The type of a participant in a meeting")
enum class MeetingParticipantType {

    TEACHER,

    PARENT,

    STUDENT,

    ;
}