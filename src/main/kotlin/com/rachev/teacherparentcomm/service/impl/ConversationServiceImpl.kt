package com.rachev.teacherparentcomm.service.impl

import com.rachev.teacherparentcomm.repository.ConversationRepository
import com.rachev.teacherparentcomm.repository.ConversationSegmentRepository
import com.rachev.teacherparentcomm.repository.models.ConversationModel
import com.rachev.teacherparentcomm.repository.models.ConversationSegmentModel
import com.rachev.teacherparentcomm.repository.models.ConversationStatus
import com.rachev.teacherparentcomm.service.ConversationService
import com.rachev.teacherparentcomm.service.dto.out.Conversation
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty

/**
 * @author Ivan Rachev
 * @since 18/09/2020
 */
@Service
@Primary
@Validated
class ConversationServiceImpl(
    private val conversationRepository: ConversationRepository,
    private val conversationSegmentRepository: ConversationSegmentRepository
) : ConversationService {

    @Transactional
    override fun startOrAppendTo(referenceId: String?, @NotEmpty content: String) {

        val dbEntry: ConversationModel =
            conversationRepository.findByReferenceId(referenceId).let {
                validateStatus(
                    Conversation(
                        participants = it?.participants ?: emptySet(),
                        segments = it?.segments ?: mutableListOf(),
                        relatedRequest = it?.relatedRequest,
                        status = it?.status
                    )
                )
                return@let it
            }?.apply {
                segments.add(
                    conversationSegmentRepository.save(
                        ConversationSegmentModel(
                            sentAt = LocalDateTime.now(),
                            content = content
                        )
                    )
                )
            } ?: ConversationModel(
                segments = mutableListOf(
                    ConversationSegmentModel(
                        sentAt = LocalDateTime.now(),
                        content = content
                    )
                )
            )

        conversationRepository.save(dbEntry)
    }



    override fun show(referenceId: String?): Conversation {
        TODO("Not yet implemented")
    }

    private fun validateStatus(conversation: Conversation) {
        if (conversation.status == ConversationStatus.CLOSED) throw IllegalStateException("Can't post to a closed convesation")
    }
}