package com.rachev.teacherparentcomm.service.dto.`in`

import com.rachev.teacherparentcomm.repository.models.StudentModel

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class ParentIn(
    val participant: ParticipantIn,
    var studentKids: MutableSet<StudentModel> = mutableSetOf()
)