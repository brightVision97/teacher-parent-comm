package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.StudentModel
import org.springframework.stereotype.Repository

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@Repository
interface StudentRepository : BaseJpaRepository<StudentModel>