package com.rachev.teacherparentcomm.repository.models

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Entity
@Table(name = "teacher")
class TeacherModel(
    var subject: String?
) : BaseMeetingParticipantModel() {

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var students = mutableSetOf<StudentModel>()
}