package com.rachev.teacherparentcomm.repository.models

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

/**
 * @author Ivan Rachev
 * @since 17/09/2020
 */
@Entity
@Table(name = "conversation_segment")
@Schema(description = "Each individual conversation message sent from either one of the involved parties")
class ConversationSegmentModel(

    @OneToOne
    @JoinColumn(name = "participant_id")
    val author: ParticipantModel? = null,

    val sentAt: LocalDateTime,

    val content: String? = null

) : AbstractJpaPersistable<Long>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ConversationSegmentModel) return false
        if (!super.equals(other)) return false

        if (author != other.author) return false
        if (sentAt != other.sentAt) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + sentAt.hashCode()
        result = 31 * result + (content?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ConversationSegmentModel(" +
            "author=$author, " +
            "sentAt=$sentAt, " +
            "content=$content" +
            ")"
    }
}