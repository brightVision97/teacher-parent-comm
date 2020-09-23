package com.rachev.teacherparentcomm.controller.form

import com.rachev.teacherparentcomm.repository.models.AbsenceReason
import io.swagger.v3.oas.annotations.Hidden
import net.bytebuddy.implementation.bind.annotation.Empty
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.lang.Nullable
import org.springframework.validation.annotation.Validated
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * @author Ivan Rachev
 * @since 09/09/2020
 */
@Validated
@Hidden
data class AbsenceForm(

    @Nullable
    val referenceId: String?,

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val start: LocalDateTime,

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val end: LocalDateTime,

    @NotNull
    var reason: AbsenceReason,

    @NotNull
    val student: StudentForm

) {

    @Validated
    @Hidden
    data class TeacherForm(

        @NotNull
        var participant: MeetingForm.ParticipantForm,

        @Nullable
        var subject: String?,

        @NotEmpty
        var students: MutableSet<StudentForm> = mutableSetOf(),

        var writtenAbsences: MutableSet<AbsenceForm> = mutableSetOf()
    )

    @Validated
    @Hidden
    data class StudentForm(

        @NotNull
        val participant: MeetingForm.ParticipantForm,

        @NotEmpty
        @Size(min = 1, max = 2)
        val parents: MutableSet<ParentForm> = mutableSetOf(),

        @Empty
        val absences: MutableSet<AbsenceForm> = mutableSetOf()
    )

    @Validated
    @Hidden
    data class ParentForm(

        @NotNull
        val participant: MeetingForm.ParticipantForm,

        var studentKids: MutableSet<StudentForm> = mutableSetOf()
    )
}