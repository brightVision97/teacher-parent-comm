package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.AbstractJpaPersistable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

/**
 * @author Ivan Rachev
 * @since 03/09/2020
 */
@NoRepositoryBean
interface AbstractJpaRepository<E : AbstractJpaPersistable<Long>> : JpaRepository<E, Long>
