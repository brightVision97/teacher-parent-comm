package com.rachev.teacherparentcomm.controller

import com.rachev.teacherparentcomm.controller.AbsenceController.Companion.URL
import com.rachev.teacherparentcomm.util.Constants.Companion.BASE_URL
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@RestController
@RequestMapping(URL, produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "absences")
class AbsenceController(

) {
    companion object {
        const val URL = "$BASE_URL/absence"
    }
}