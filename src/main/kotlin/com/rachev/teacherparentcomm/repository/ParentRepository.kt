package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.ParentModel
import org.springframework.stereotype.Repository

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Repository
interface ParentRepository : BaseJpaRepository<ParentModel>