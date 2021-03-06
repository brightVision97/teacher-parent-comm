package com.rachev.teacherparentcomm.controller

import com.rachev.teacherparentcomm.controller.MeetingController.Companion.URL
import com.rachev.teacherparentcomm.controller.form.MeetingForm
import com.rachev.teacherparentcomm.service.MeetingService
import com.rachev.teacherparentcomm.service.dto.`in`.MeetingIn
import com.rachev.teacherparentcomm.util.Constants.Companion.BASE_URL
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author Ivan Rachev
 * @since 03/09/2020
 */
@RestController
@RequestMapping(URL, produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "meetings")
internal class MeetingController(
    private val meetingService: MeetingService
) {

    companion object {
        const val URL = "$BASE_URL/meetings"
    }

    @Operation(description = "Returns a list of all found meetings")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successfully list all meetings"
        )
    )
    @GetMapping
    fun find() = meetingService.findAll()

    @Operation(description = "Initiates a new booked meeting")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successfully save the entity and return its json as response"
        )
    )
    @PostMapping("/bulk")
    fun createOrUpdate(
        @RequestBody @Valid form: MeetingForm
    ) = meetingService.createOrUpdate(MeetingIn.map(form))
}
