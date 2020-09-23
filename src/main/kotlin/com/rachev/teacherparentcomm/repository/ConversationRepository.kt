package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.ConversationModel

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
interface ConversationRepository : AbstractJpaRepository<ConversationModel> {

    fun findByReferenceId(referenceId: String?): ConversationModel?
}