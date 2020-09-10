package com.rachev.teacherparentcomm.repository.models

import org.hibernate.annotations.NaturalId
import org.springframework.data.util.ProxyUtils
import java.io.Serializable
import java.util.UUID
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@MappedSuperclass
abstract class AbstractJpaPersistable<E> where E : Serializable, E : Number {

    companion object {
        const val serialVersionUID = -5554308939380869754L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: E? = null

    @NaturalId
    val referenceId: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {

        if (other == null || other !is AbstractJpaPersistable<*>) return false

        if (this === other) return true

        if (this.javaClass != ProxyUtils.getUserClass(other)) return false

        if (referenceId != other.referenceId) return false

        return true
    }

    override fun hashCode() = referenceId?.hashCode() ?: 0
}