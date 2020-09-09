package com.rachev.teacherparentcomm.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author Ivan Rachev
 * @since 03/09/2020
 */
@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(ex: RuntimeException) =
        ResponseEntity(ex.message, HttpStatus.INTERNAL_SERVER_ERROR)
}