package com.rachev.teacherparentcomm.repository.models

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@MappedSuperclass
abstract class BaseModel : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null
}