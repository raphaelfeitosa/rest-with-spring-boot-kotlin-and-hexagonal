package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.exceptions

import java.time.LocalDateTime

class ErrorMessageResponse(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val code: String? = "unknown",
    val message: String?,
    val path: String,
    val errors: HashMap<String, String?>
)