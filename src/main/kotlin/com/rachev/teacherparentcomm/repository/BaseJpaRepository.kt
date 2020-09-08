package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.BaseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.NoRepositoryBean

/**
 * @author Ivan Rachev
 * @since 03/09/2020
 */
@NoRepositoryBean
interface BaseJpaRepository<E: BaseModel> : JpaRepository<E, Long>, JpaSpecificationExecutor<E>
