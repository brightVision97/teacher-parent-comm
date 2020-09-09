package com.rachev.teacherparentcomm.controller

import com.rachev.teacherparentcomm.controller.AbsenceController.Companion.URL
import com.rachev.teacherparentcomm.controller.form.AbsenceForm
import com.rachev.teacherparentcomm.service.AbsenceService
import com.rachev.teacherparentcomm.service.dto.`in`.AbsenceIn
import com.rachev.teacherparentcomm.util.Constants.Companion.BASE_URL
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.ServletRequestUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author Ivan Rachev
 * @since 08/09/2020
 */
@RestController
@RequestMapping(URL, produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "absences")
internal class AbsenceController(
    private val absenceService: AbsenceService
) {
    companion object {
        const val URL = "$BASE_URL/absences"
    }

    @Operation(description = "Returns a list of all found absences")
    @GetMapping("/all")
    fun find() = absenceService.findAll()

    @Operation(description = "Endpoint for persisting absences of students")
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "Successfully save the entity and return its json as response"
        )
    )
    @PostMapping("/save")
    fun create(
        @RequestBody @Valid form: AbsenceForm
    ) = absenceService.save(AbsenceIn.map(form))
}