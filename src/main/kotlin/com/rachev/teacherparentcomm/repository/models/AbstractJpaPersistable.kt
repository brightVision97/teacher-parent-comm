package com.rachev.teacherparentcomm.repository.models

import org.hibernate.annotations.Immutable
import org.hibernate.annotations.NaturalId
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.util.UUID
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Immutable
@MappedSuperclass
abstract class AbstractJpaPersistable<E : Serializable> {

    companion object {
        const val serialVersionUID = -5554308939380869754L
    }

    @Id
    @GeneratedValue
    private val id: E? = null

    @NaturalId
    private val referenceId: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {

        if (other == null || other !is AbstractJpaPersistable<*>) return false

        if (this === other) return true

        if (this.javaClass != ProxyUtils.getUserClass(other)) return false

        if (referenceId != other.referenceId) return false

        return true
    }

    @Suppress("SENSELESS_COMPARISON")
    override fun hashCode() =
        referenceId.hashCode() ?: 0
}