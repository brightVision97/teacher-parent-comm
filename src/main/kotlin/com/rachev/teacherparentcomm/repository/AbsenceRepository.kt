package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.AbsenceModel

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
interface AbsenceRepository : AbstractJpaRepository<AbsenceModel> {

    fun findByReferenceId(referenceId: String): AbsenceModel
}