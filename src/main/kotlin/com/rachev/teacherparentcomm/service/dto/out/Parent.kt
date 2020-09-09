package com.rachev.teacherparentcomm.service.dto.out

import com.rachev.teacherparentcomm.repository.models.MeetingParticipant
import com.rachev.teacherparentcomm.repository.models.StudentModel

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class Parent(
    val participant: MeetingParticipant,
    var studentKids: MutableSet<StudentModel> = mutableSetOf()
)