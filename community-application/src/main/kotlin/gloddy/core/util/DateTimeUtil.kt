package gloddy.core.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter.*

fun LocalDateTime.toResponse(): String =
    this.format(ofPattern("yyyy-MM-dd hh:mm"))
        .replace(" ", "T")