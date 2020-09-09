package com.rachev.teacherparentcomm

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
@EnableAsync
@OpenAPIDefinition(
    tags = [
        Tag(name = "meetings", description = "Operations revolving around meetings"),
        Tag(name = "absences", description = "Operations revolving around absences")
    ],
    info = Info(
        title = "Meetings Communication API",
        version = "0.0.1",
        contact = Contact(
            name = "Ivan Rachev",
            email = "ivan.rachev97@gmail.com"
        )
    )
)
class TeacherParentCommApplication

fun main(args: Array<String>) {
    runApplication<TeacherParentCommApplication>(*args)
}
