package com.rachev.teacherparentcomm.repository.models

import org.hibernate.annotations.NaturalId
import java.util.UUID
import javax.persistence.MappedSuperclass

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Suppress("SENSELESS_COMPARISON")
@MappedSuperclass
abstract class BaseReferenceModel : BaseModel() {

    @NaturalId
    open var referenceId: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other !is BaseReferenceModel) return false
        if (referenceId != other.referenceId) return false

        return true
    }

    override fun hashCode(): Int {
        return if (referenceId == null) 0
        else referenceId.hashCode()
    }
}