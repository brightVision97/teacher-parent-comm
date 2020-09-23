package com.rachev.teacherparentcomm.service

import com.rachev.teacherparentcomm.service.dto.out.Conversation
import javax.validation.constraints.NotEmpty

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
interface ConversationService {

    fun startOrAppendTo(referenceId: String? = null, @NotEmpty content: String)

    fun show(referenceId: String?): Conversation
}