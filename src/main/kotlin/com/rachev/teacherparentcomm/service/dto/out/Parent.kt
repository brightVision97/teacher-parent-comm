package com.rachev.teacherparentcomm.service.dto.out

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
data class Parent(
    val participant: Participant,
    var studentKids: MutableSet<Student> = mutableSetOf()
)