package com.rachev.teacherparentcomm.repository

import com.rachev.teacherparentcomm.repository.models.MeetingModel
import org.springframework.stereotype.Repository

/**
 * @author Ivan Rachev
 * @since 04/09/2020
 */
@Repository
interface MeetingRepository : BaseJpaRepository<MeetingModel>